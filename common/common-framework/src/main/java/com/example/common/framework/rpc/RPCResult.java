package com.example.common.framework.rpc;


import com.example.common.framework.enums.RPCResultEnum;

import java.io.Serializable;

/**
 * RPCResult
 * 集群微服务间通讯的响应格式
 *
 * @author liangxiao
 * @version 1.00
 * @Date 2020/11/22 23:01
 */
public class RPCResult<T> implements Serializable {

    private Integer code;
    private String errMsg;
    private T result;

    public RPCResult() {
    }

    /**
     * 构建main微服务标准响应结果结构
     *
     * @param code 返回码
     * @param errMsg 返回的异常信息
     * @param result 返回结果
     * @return
     */
    public RPCResult(Integer code, String errMsg, T result) {
        this.code = code;
        this.errMsg = errMsg;
        this.result = result;
    }

    /**
     * 构建main微服务标准响应结果结构
     *
     * @param code 返回码
     * @param errMsg 返回的异常信息
     * @return
     */
    public RPCResult(Integer code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    /**
     * 构建微服务标准响应结果结构
     * - 成功且有数据
     *
     * @param result 返回结果
     * @return
     */
    public static <T> RPCResult<T> buildSuccess(T result) {
        return new RPCResult<>(RPCResultEnum.SUCCESS.getCode(), RPCResultEnum.SUCCESS.getDesc(), result);
    }

    /**
     * 构建微服务标准响应结果结构
     * - 传入失败码
     *
     * @param code 返回码
     * @param errMsg 返回的异常信息
     * @return
     */
    public static <T> RPCResult<T> buildError(int code, String errMsg) {
        return new RPCResult<>(code, errMsg);
    }

    /**
     * 构建微服务标准响应结果结构
     * - 默认失败码
     *
     * @param errMsg 返回的异常信息
     * @return
     */
    public static <T> RPCResult<T> buildError(String errMsg) {
        return new RPCResult<>(RPCResultEnum.ERROR.getCode(), errMsg);
    }

    /**
     * 响应数据是否成功
     *
     * @return
     */
    public boolean success() {
        return this.code == RPCResultEnum.SUCCESS.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
