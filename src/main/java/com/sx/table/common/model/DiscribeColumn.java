package com.sx.table.common.model;

import java.util.HashMap;

/**
 * 用于desc table;但是想了一下 还是List<HashMap<String,String>>好用;
 * hashmap方便扩展;
 * 实体类方便和数据表对应;
 * linkhashmap方便扩展和顺序读取
 *
 * @author sunxu93@163.com
 * @date 19/7/19/019 11:27
 */
public class DiscribeColumn {
    private String type;
    private Boolean primaryKey;
    private Boolean enableNull;
    private String name;
    private String comment;

    public DiscribeColumn() {
    }

    public DiscribeColumn(String type, Boolean primaryKey, Boolean enableNull, String name, String comment) {
        this.type = type;
        this.primaryKey = primaryKey;
        this.enableNull = enableNull;
        this.name = name;
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Boolean getEnableNull() {
        return enableNull;
    }

    public void setEnableNull(Boolean enableNull) {
        this.enableNull = enableNull;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
