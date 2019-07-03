/**
 * DiLing.com Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */

package com.sx.table.common;

import java.io.Serializable;

/**
 * @author hekai
 * @version $Id: Result, v 0.1 2018/3/14 下午2:00 hekai Exp $
 */
public class Result implements Serializable{

    private static final long serialVersionUID = 3027568518924970161L;

    private Object content;

    private boolean success;

    private String message;

    public Result(Object content, boolean success, String message) {
        this.content = content;
        this.success = success;
        this.message = message;
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
