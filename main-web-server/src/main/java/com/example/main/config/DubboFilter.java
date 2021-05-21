package com.example.main.config;

import com.alibaba.csp.sentinel.*;
import com.alibaba.csp.sentinel.adapter.dubbo.DubboUtils;
import com.alibaba.csp.sentinel.adapter.dubbo.SentinelDubboConsumerFilter;
import com.alibaba.csp.sentinel.adapter.dubbo.config.DubboAdapterGlobalConfig;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.support.RpcUtils;

import java.util.LinkedList;
import java.util.Optional;

import static org.apache.dubbo.common.constants.CommonConstants.CONSUMER;

/**
 * DubboFilter
 * 重新扩展sentinel-dubbo-filter
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/3/14 23:40
 */
@Activate(group = CONSUMER, order = -9999)
public class DubboFilter extends SentinelDubboConsumerFilter implements Filter {

    String getMethodName(Invoker<?> invoker, Invocation invocation, String prefix) {
        return DubboUtils.getMethodResourceName(invoker, invocation, prefix);
    }

    String getInterfaceName(Invoker<?> invoker, String prefix) {
        return DubboUtils.getInterfaceName(invoker, prefix);
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        InvokeMode invokeMode = RpcUtils.getInvokeMode(invoker.getUrl(), invocation);
        if (InvokeMode.SYNC == invokeMode) {
            return syncInvoke(invoker, invocation);
        } else {
            return asyncInvoke(invoker, invocation);
        }
    }

    /**
     * todo 超出熔断的那个服务
     *
     * @param invoker
     * @param invocation
     * @return
     */
    private Result syncInvoke(Invoker<?> invoker, Invocation invocation) {
        Entry interfaceEntry = null;
        Entry methodEntry = null;
        Entry providerEntry = null;
        String prefix = DubboAdapterGlobalConfig.getDubboConsumerResNamePrefixKey();
        String interfaceResourceName = getInterfaceName(invoker, prefix);
        String methodResourceName = getMethodName(invoker, invocation, prefix);
        // provider的信息
        String hostPort = invoker.getUrl().getHost() + ":" + invoker.getUrl().getPort();
        try {
            interfaceEntry = SphU.entry(interfaceResourceName, ResourceTypeConstants.COMMON_RPC, EntryType.OUT);
//            methodEntry = SphU.entry(methodResourceName, ResourceTypeConstants.COMMON_RPC, EntryType.OUT,
//                    invocation.getArguments());
            // 注册服务端资源
            providerEntry = SphU.entry(methodResourceName + "@" + hostPort, ResourceTypeConstants.COMMON_RPC, EntryType.OUT, invocation.getArguments());

            Result result = invoker.invoke(invocation);
            if (result.hasException()) {
                Tracer.traceEntry(result.getException(), interfaceEntry);
//                Tracer.traceEntry(result.getException(), methodEntry);
                Tracer.traceEntry(result.getException(), providerEntry);
            }
            return result;
        } catch (BlockException e) {
            return DubboAdapterGlobalConfig.getConsumerFallback().handle(invoker, invocation, e);
        } catch (RpcException e) {
            Tracer.traceEntry(e, interfaceEntry);
//            Tracer.traceEntry(e, methodEntry);
            Tracer.traceEntry(e, providerEntry);
            throw e;
        } finally {
            if (providerEntry != null) {
                providerEntry.exit();
            }
            if (methodEntry != null) {
                methodEntry.exit(1, invocation.getArguments());
            }
            if (interfaceEntry != null) {
                interfaceEntry.exit();
            }
        }
    }

    private Result asyncInvoke(Invoker<?> invoker, Invocation invocation) {
        LinkedList<EntryHolder> queue = new LinkedList<>();
        String prefix = DubboAdapterGlobalConfig.getDubboConsumerResNamePrefixKey();
        String interfaceResourceName = getInterfaceName(invoker, prefix);
        String methodResourceName = getMethodName(invoker, invocation, prefix);
        try {
            queue.push(new EntryHolder(
                    SphU.asyncEntry(interfaceResourceName, ResourceTypeConstants.COMMON_RPC, EntryType.OUT), null));
            queue.push(new EntryHolder(
                    SphU.asyncEntry(methodResourceName, ResourceTypeConstants.COMMON_RPC,
                            EntryType.OUT, 1, invocation.getArguments()), invocation.getArguments()));
            Result result = invoker.invoke(invocation);
            result.whenCompleteWithContext((r, throwable) -> {
                Throwable error = throwable;
                if (error == null) {
                    error = Optional.ofNullable(r).map(Result::getException).orElse(null);
                }
                while (!queue.isEmpty()) {
                    EntryHolder holder = queue.pop();
                    Tracer.traceEntry(error, holder.entry);
                    exitEntry(holder);
                }
            });
            return result;
        } catch (BlockException e) {
            while (!queue.isEmpty()) {
                exitEntry(queue.pop());
            }
            return DubboAdapterGlobalConfig.getConsumerFallback().handle(invoker, invocation, e);
        }
    }

    static class EntryHolder {

        final private Entry entry;
        final private Object[] params;

        public EntryHolder(Entry entry, Object[] params) {
            this.entry = entry;
            this.params = params;
        }
    }

    private void exitEntry(EntryHolder holder) {
        if (holder.params != null) {
            holder.entry.exit(1, holder.params);
        } else {
            holder.entry.exit();
        }
    }
}