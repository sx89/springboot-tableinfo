package com.sx.table.biz.impl;

import com.sx.table.biz.TableBiz;
import com.sx.table.core.dao.jpa.TableNameRepository;
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
public class TableBizImpl implements TableBiz {
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
            logger.error("TableBizImpl[descTable] error", e);
            return null;
        }
    }

    @Override
    public List<HashMap<String, String>> showTables(String databaseName) {
        List<HashMap<String, String>> hashMaps = nativeSqlFromEM.showTables(databaseName);
        return hashMaps;
    }

    @Override
    public List<HashMap<String, String>> selectTable(String tableName) {
        List<HashMap<String, String>> hashMaps = nativeSqlFromEM.selectTable(tableName);
        return hashMaps;
    }
}
