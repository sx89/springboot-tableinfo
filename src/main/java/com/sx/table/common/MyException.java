package com.sx.table.common;

/**
 * @author sunxu93@163.com
 * @date 19/7/17/017 17:01
 */
public class MyException extends Exception {
    ErrorCode errorCode;

    public MyException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public MyException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public MyException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public MyException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
