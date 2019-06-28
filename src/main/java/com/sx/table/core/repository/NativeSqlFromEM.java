package com.sx.table.core.repository;

import com.sx.table.common.TableFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * 使用原生sql做desc table;是因为jpa在对 "desc ?1"注入tablename的时候;会变成desc 'tablename'; 带了单引号导致语法错误
 *
 * 使用原生sql做show table data是因为后续要做join操作,jpa对多表join并不灵活
 *
 * @author  sx
 *
 */
@Service
public class NativeSqlFromEM {


    @Autowired
    private EntityManager entityManager;

    public List<HashMap<String, String>> descTable(String tablename) {
        Query query = entityManager.createNativeQuery("DESCRIBE " + tablename);
        List<Object[]> list = query.getResultList();

        String[] tableFeildNames = {"columnName", "type", "null", "primaryKey"};

        List<HashMap<String, String>> hashMaps = new ArrayList<HashMap<String, String>>();

        for (Object[] temp : list) {
            HashMap<String, String> stringStringHashMap = new HashMap<>();
            for (int i = 0; i < tableFeildNames.length; i++) {
                stringStringHashMap.put(tableFeildNames[i], temp[i].toString());
            }
            hashMaps.add(stringStringHashMap);
        }
        return hashMaps;
    }

    /**
     *
     * @param databaseName
     * @return
     */
    public List<HashMap<String, String>> showTables(String databaseName) {
        Query query = entityManager.createNativeQuery("show tables from " + databaseName);

        //query.getResultList()的返回值有时候是list<String[]> 有时候是list<String>
        List<String> list = query.getResultList();
        String tableFeildNames = "tableName";

        List<HashMap<String, String>> hashMaps = new ArrayList<HashMap<String, String>>();

        for (int j = 0; j < list.size(); j++) {
            String temp = list.get(j);

            HashMap<String, String> stringStringHashMap = new HashMap<>();
            stringStringHashMap.put(tableFeildNames, temp);
            stringStringHashMap.put("模型说明","空");
            stringStringHashMap.put("类型","空");
            hashMaps.add(stringStringHashMap);
        }
        return hashMaps;
    }
}


