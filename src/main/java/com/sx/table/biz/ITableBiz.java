package com.sx.table.biz;

import java.util.HashMap;
import java.util.List;

/**
 * 主要功能join,orderby,groupby,高级搜索
 *
 * @author sunxu93@163.com
 * @date 19/6/28/028 14:03
 */
public interface ITableBiz {

    List<HashMap> showJoinTableData();

    /**
     * descTable
     */
    List<HashMap<String, String>> descTable(String tableName);



    /**
     * show tables;
     */
    List<HashMap<String, String>> showTables(String databaseName);

    /**
     * select * from Table;
     */
    List<HashMap<String, String>> selectTable(String tableName);
}
