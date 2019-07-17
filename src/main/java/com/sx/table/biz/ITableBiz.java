package com.sx.table.biz;

import com.sx.table.common.ColumnInfo;
import com.sx.table.common.MyException;
import java.util.HashMap;
import java.util.List;

/**
 * 主要功能join,orderby,groupby,高级搜索
 *
 * @author sunxu93@163.com
 * @date 19/6/28/028 14:03
 */
public interface ITableBiz {


    /**
     * 查找数据库所有的表
     */
    List<HashMap<String, String>> showTables(String databaseName);

    /**
     * 查找表的数据
     */
    List<HashMap<String, String>> selectTable(String tableName);


    /**
     * 拿到表所有的字段
     */
    List<HashMap<String, String>> getAllColumn(String tableName);

    /**
     * 创建新字段
     * @param columnInfo
     * @return
     */
    Boolean createColumn(ColumnInfo columnInfo) throws MyException;

    /**
     * 更新字段信息
     * @param columnInfo
     * @return
     */
    Boolean updateColumn(ColumnInfo columnInfo)throws MyException;

    /**
     * 删除字段
     * @param conlumnInfo
     * @return
     */
    Boolean deleteColumn(ColumnInfo conlumnInfo) throws MyException;


    /**
     * 展示表的join信息
     * @return
     */
    List<HashMap> showJoinTableData();

    /**
     * 创建表
     * @param tableName
     * @param list
     * @return
     */

    Object createTable(String tableName, List<ColumnInfo> list);
}
