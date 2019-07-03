package com.sx.table.biz.impl;

import com.sx.table.biz.TableBiz;
import com.sx.table.core.dao.jpa.TableNameRepository;
import com.sx.table.core.dao.nativesql.NativeSqlFromEM;
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
    @Autowired
    NativeSqlFromEM nativeSqlFromEM;

    @Override
    public List<HashMap> showJoinTableData() {
        return null;
    }

    @Override
    public List<HashMap<String, String>> descTable(String tableName) {
        List<HashMap<String, String>> hashMaps = nativeSqlFromEM.descTable(tableName);

        return hashMaps;
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
