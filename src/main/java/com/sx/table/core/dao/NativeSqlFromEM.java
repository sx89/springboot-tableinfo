package com.sx.table.core.dao;

import com.sx.table.common.enums.ErrorCode;
import com.sx.table.common.exception.MyException;
import com.sx.table.common.model.CrudColumnInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 技术选择思路:
 * <p>
 * 使用原生sql做desc table;是因为jpa在对 "desc ?1"注入tablename的时候;会变成desc 'tablename'; 带了单引号导致语法错误
 * <p>
 * jpa对多表join并不灵活,需要在查的表的实体类内部做关联,不适合任意表之间join
 * <p>
 * 不使用类来存储信息而是HashMap;是因为类有哪些变量需要根据具体的表有哪些字段来定制,而HashMap可以根据不同的表的字段数动态扩张
 * 后期会有一百多张表,每一个表创建一个实体类也不太现实,不如每个表对应一个HashMap
 * <p>
 * 使用mycat做中间件,原生sql做join查询
 *
 * 参考博客:https://www.cnblogs.com/zhouguanglin/p/7625655.html
 * 
 * @author sx
 */
@Service
public class NativeSqlFromEM {

    public static final Logger logger = LoggerFactory.getLogger(NativeSqlFromEM.class);

    @Autowired
    private EntityManager entityManager;


    /**
     * 展示数据库中所有的表
     *
     * @param databaseName
     * @return
     */
    public List<HashMap<String, String>> showTables(String databaseName) {
        Query query;
        try {
            if (StringUtils.isEmpty(databaseName)) {
                query = entityManager.createNativeQuery("show tables;");
            }else {

                query = entityManager.createNativeQuery("show tables from " + databaseName);
            }

            //query.getResultList()的返回值有时候是list<String[]> 有时候是list<String>; 当返回单列的时候是list<String>
            List<String> list = query.getResultList();
            String tableFeildNames = "tableName";

            List<HashMap<String, String>> hashMaps = new ArrayList<HashMap<String, String>>();

            for (int j = 0; j < list.size(); j++) {
                String temp = list.get(j);

                HashMap<String, String> stringStringHashMap = new HashMap<>();
                stringStringHashMap.put(tableFeildNames, temp);
                //TODO 数据将来从另一个数据表获取填充
                stringStringHashMap.put("模型说明", "空");
                stringStringHashMap.put("类型", "空");
                hashMaps.add(stringStringHashMap);
            }

            return hashMaps;
        } catch (Exception e) {
            logger.error("获取数据表失败", e);
            return null;
        }
    }

    /**
     * 展示表的字段信息
     *
     * @param tablename
     * @return
     */
    public List<HashMap<String, String>> descTable(String tablename) throws MyException {

        if (StringUtils.isEmpty(tablename)) {
            throw new MyException("传入参数为空", ErrorCode.ERROR_PARAM_NULL);
        }
        Query query = entityManager.createNativeQuery("DESCRIBE " + tablename);
        List<Object[]> list = query.getResultList();

        String[] tableFeildNames = {"columnName", "type", "null", "primaryKey", "comment"};

        List<HashMap<String, String>> hashMaps = new ArrayList<>();

        try {
            for (Object[] temp : list) {
                HashMap<String, String> stringStringHashMap = new HashMap<>();

                for (int i = 0; i < tableFeildNames.length; i++) {
                    //TODO 如果要填充comment了,那就暂时填充null,并跳过,后面从其他数据库获取注释信息
                    if (i == tableFeildNames.length - 1) {
                        stringStringHashMap.put(tableFeildNames[i], "");
                        continue;
                    }
                    if (null != temp[i]) {
                        stringStringHashMap.put(tableFeildNames[i], temp[i].toString());
                    } else {
                        stringStringHashMap.put(tableFeildNames[i], "");
                    }
                }
                hashMaps.add(stringStringHashMap);
            }
            return hashMaps;
        } catch (Exception e) {
            logger.error("获取数据表字段失败", e);
            return null;
        }
    }

    /**
     * 展示表数据 (select * from table;)
     *
     * @param tableName
     * @return
     */
    public List<HashMap<String, String>> selectTable(String tableName) throws MyException {
        if (StringUtils.isEmpty(tableName)) {
            throw new MyException(ErrorCode.ERROR_PARAM_NULL);
        }
        try {
            //把表的每个字段名查出来
            Query query = entityManager.createNativeQuery("desc " + tableName);
            List<Object[]> list = query.getResultList();
            List<String> tableColumnName = list.stream().map(arr -> {
                return String.valueOf(arr[0]);
            }).collect(Collectors.toList());


            //把表的数据查出来
            query = entityManager.createNativeQuery("select * from " + tableName);
            list = query.getResultList();

            //把表的字段名和数据拼在一起返回tableData
            List<HashMap<String, String>> tableData = new ArrayList<>();

            for (int j = 0; j < list.size(); j++) {
                HashMap<String, String> rowDataHash = new HashMap<>();
                Object[] rowDataList = list.get(j);
                for (int i = 0; i < tableColumnName.size(); i++) {
                    if (null != rowDataList[i]) {
                        rowDataHash.put(tableColumnName.get(i), rowDataList[i].toString());
                    } else {
                        rowDataHash.put(tableColumnName.get(i), null);

                    }

                }
                tableData.add(rowDataHash);
            }
            return tableData;
        } catch (Exception e) {
            logger.error("获取数据表信息失败", e);
            return null;
        }
    }

    /**
     * alter table user add COLUMN new1 VARCHAR(20) DEFAULT NULL NOT NULL COMMENT '123';
     * 增加字段,并把新的字段信息返回回去
     */
    public Boolean createColum(CrudColumnInfo columnInfo) {
        try {
            //TODO  for循环中用append  像这种可读性很差的,我应该用字符串+ 的形式的
            StringBuilder sql = new StringBuilder();
            sql.append("alter table ");
            sql.append(columnInfo.getBelongTable());
            sql.append(" add column ");
            sql.append(columnInfo.getName() + " ");

            sql.append(columnInfo.getType() + "(" + columnInfo.getTypeLength() + ") ");
            // TODO 默认值区分字符和数字
            if (!StringUtils.isEmpty(columnInfo.getDefaultValue())) {
                sql.append("default " + columnInfo.getDefaultValue() + " ");
            }
            if (!columnInfo.getEnableNull()) {
                sql.append(" not null ");
            }
            if (!StringUtils.isEmpty(columnInfo.getComment())) {
                sql.append(" comment ");
                sql.append("\'" + columnInfo.getComment() + "\';");
            }
            Query query = entityManager.createNativeQuery(sql.toString());
            return true;
        } catch (Exception e) {
            logger.error("创建字段失败",e);
            return false;
        }
    }

    /**
     * 删除一个字段
     * alter table user DROP COLUMN new2;
     * @param columnInfo
     * @return
     */
    public Boolean deleteColumn(CrudColumnInfo columnInfo) {
        String sql = null;
        String belongTable = columnInfo.getBelongTable();
        String name = columnInfo.getName();
        if (columnInfo == null||StringUtils.isEmpty(belongTable) || StringUtils.isEmpty(name)) {
            logger.error("传入参数为空", new MyException(ErrorCode.ERROR_PARAM_NULL));
            return false;
        }
        try {
            sql = "alter table " + belongTable + " drop colum " + name + " ;";
            entityManager.createNativeQuery(sql);
            return true;
        } catch (Exception e) {
            logger.error("删除字段失败", e);
            return false;
        }

    }

}


