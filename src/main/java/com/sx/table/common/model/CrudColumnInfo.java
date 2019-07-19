package com.sx.table.common.model;

/**
 * 增删改字段所需要的字段信息
 * @author sunxu93@163.com
 * @date 19/7/12/012 11:46
 */
public class CrudColumnInfo {
    private  String belongTable;
    private String type;
    //TODO 可能是int
    private String typeLength;
    private String defaultValue;
    private String name;
    private String comment;
    private Boolean enableNull;

    public CrudColumnInfo() {
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getBelongTable() {
        return belongTable;
    }

    public void setBelongTable(String belongTable) {
        this.belongTable = belongTable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeLength() {
        return typeLength;
    }

    public void setTypeLength(String typeLength) {
        this.typeLength = typeLength;
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

    public Boolean getEnableNull() {
        return enableNull;
    }

    public void setEnableNull(Boolean enableNull) {
        this.enableNull = enableNull;
    }
}

