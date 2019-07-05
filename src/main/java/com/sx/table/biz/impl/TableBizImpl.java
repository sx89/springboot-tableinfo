package com.sx.table.biz.impl;

import com.sx.table.biz.ITableBiz;
import com.sx.table.core.dao.nativesql.NativeSqlFromEM;
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
    public List<HashMap> showJoinTableData() {
        return null;
    }

    @Override
    public List<HashMap<String, String>> descTable(String tableName) {
        try {
            List<HashMap<String, String>> hashMaps = nativeSqlFromEM.descTable(tableName);
            return hashMaps;
        } catch (Exception e) {
            logger.error("[TableBizImpl](descTable) 执行异常", e);
            return null;
        }
    }

    @Override
    public List<HashMap<String, String>> showTables(String databaseName) {
        try {
            List<HashMap<String, String>> hashMaps = nativeSqlFromEM.showTables(databaseName);
            return hashMaps;
        } catch (Exception e) {
            logger.error("[TableBizImpl](descTable) 执行异常",e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<HashMap<String, String>> selectTable(String tableName) {
        try {
            List<HashMap<String, String>> hashMaps = nativeSqlFromEM.selectTable(tableName);
            return hashMaps;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[TableBizImpl](selectTable) 执行异常",e);
            return null;
        }
    }
}
