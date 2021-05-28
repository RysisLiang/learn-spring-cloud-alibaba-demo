package com.example.common.framework.enums;

/**
 * RPCResultEnum
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/3/19 17:12
 */
public enum RPCResultEnum {

    SUCCESS(0, ""),
    ERROR(100, "异常");

    private final int code;
    private final String desc;

    RPCResultEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
