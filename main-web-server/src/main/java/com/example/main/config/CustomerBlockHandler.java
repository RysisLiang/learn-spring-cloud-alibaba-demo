package com.example.main.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CustomerBlockHandler
 * web响应自定义test
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/2/23 17:20
 */
@Component
@Configuration
public class CustomerBlockHandler implements BlockExceptionHandler {

    /**
     * 全局统一接口限流处理
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws Exception
     */
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        SentinelErrorMsg sentinelErrorMsg = new SentinelErrorMsg();
        if (e instanceof FlowException) {
            sentinelErrorMsg.setMsg("接口限流了");
            sentinelErrorMsg.setStatus(101);
        } else if (e instanceof DegradeException) {
            sentinelErrorMsg.setMsg("服务降级了");
            sentinelErrorMsg.setStatus(102);
        } else if (e instanceof ParamFlowException) {
            sentinelErrorMsg.setMsg("热点参数限流了");
            sentinelErrorMsg.setStatus(103);
        } else if (e instanceof SystemBlockException) {
            sentinelErrorMsg.setMsg("系统规则（负载/...不满足要求）");
            sentinelErrorMsg.setStatus(104);
        } else if (e instanceof AuthorityException) {
            sentinelErrorMsg.setMsg("授权规则不通过");
            sentinelErrorMsg.setStatus(105);
        }
        // http状态码
        httpServletResponse.setStatus(505);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        // spring mvc自带的json操作工具，叫jackson
        new ObjectMapper().writeValue(httpServletResponse.getWriter(), sentinelErrorMsg);
    }

    @PostConstruct
    public void init() {
        new CustomerBlockHandler();
    }

    static class SentinelErrorMsg {
        private Integer status;
        private String msg;


        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
