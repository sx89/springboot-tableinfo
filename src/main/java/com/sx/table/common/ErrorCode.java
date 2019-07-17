package com.sx.table.common;

import org.apache.commons.lang.StringUtils;

/**
 * 系统错误代码
 * @author sunxu93@163.com
 * @date 19/7/17/017 16:49
 */
public enum ErrorCode {
    /**
     * 普通error
     */
    ERROE_COMMON("ERROR_COMMON", "普通的error"),
    /**
     * 参数为空error
     */
    ERROR_PARAM_NULL("ERROR_PARAM_NULL", "参数为空");
    private String code;
    private String describe;

    ErrorCode(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }


    public static ErrorCode valueByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (ErrorCode temp : ErrorCode.values()) {
            if (temp.code.equals(code)) {
                return temp;
            }
        }
        return null;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }}
