package com.sx.table.biz.impl;

import com.sx.table.biz.ITableBiz;
import com.sx.table.common.enums.ErrorCode;
import com.sx.table.common.exception.MyException;
import com.sx.table.common.model.CrudColumnInfo;
import com.sx.table.core.dao.NativeSqlFromEM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author sunxu93@163.com
 * @date 19/6/28/028 14:04
 */
@Service
public class TableBizImpl implements ITableBiz {

    private static final Logger logger = LoggerFactory.getLogger(TableBizImpl.class);

    @Autowired
    NativeSqlFromEM nativeSqlFromEM;

    @Override
    public List<HashMap<String, String>> getAllColumn(String tableName) {
        try {
            List<HashMap<String, String>> hashMaps = nativeSqlFromEM.descTable(tableName);
            return hashMaps;
        } catch (Exception e) {
            logger.error("[TableBizImpl](getAllColumn) 执行异常", e);
            return null;
        }
    }

    @Override
    public List<HashMap<String, String>> showTables(String databaseName) {
        try {
            List<HashMap<String, String>> hashMaps = nativeSqlFromEM.showTables(databaseName);
            return hashMaps;
        } catch (Exception e) {
            logger.error("[TableBizImpl](getAllColumn) 执行异常", e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<HashMap<String, String>> selectFromTable(String tableName) {
        try {
            List<HashMap<String, String>> hashMaps = nativeSqlFromEM.selectTable(tableName);
            return hashMaps;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[TableBizImpl](selectTable) 执行异常",e);
            return null;
        }

    }


//字段功能过于危险,暂时不开发
//
//    mysql修改字段名：
//    ALTER  TABLE 表名 CHANGE 旧字段名 新字段名 新数据类型;
//    alter  table table1 change column1 column1 varchar(100) DEFAULT 1.2 COMMENT '注释'; -- 正常，此时字段名称没有改变，能修改字段类型、类型长度、默认值、注释
//    alter  table table1 change column1 column2 decimal(10,1) DEFAULT NULL COMMENT '注释' -- 正常，能修改字段名、字段类型、类型长度、默认值、注释
//    alter  table table1 change column2 column1 decimal(10,1) DEFAULT NULL COMMENT '注释' -- 正常，能修改字段名、字段类型、类型长度、默认值、注释

    @Override
    public Boolean createColumn(CrudColumnInfo columnInfo) throws MyException {
        if (columnInfo == null) {
            logger.error("字段信息不能为空", new MyException(ErrorCode.ERROR_PARAM_NULL));
            return false;
        }
        try {
            nativeSqlFromEM.createColum(columnInfo);
            return true;
        } catch (Exception e) {
            logger.error("创建字段失败", e);
            return false;
        }
    }

    @Override
    public Boolean updateColumn(CrudColumnInfo columnInfo) {
        return null;


    }

    @Override
    public Boolean deleteColumn(CrudColumnInfo columnInfo) {
        if (columnInfo == null) {
            logger.error("字段信息不能为空", new MyException(ErrorCode.ERROR_PARAM_NULL));
            return false;
        }
        try {
            nativeSqlFromEM.deleteColumn(columnInfo);
            return true;
        } catch (Exception e) {
            logger.error("删除字段失败", e);
            return false;
        }
    }
}
