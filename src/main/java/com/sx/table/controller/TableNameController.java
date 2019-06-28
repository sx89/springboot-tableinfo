package com.sx.table.controller;

import com.sx.table.common.TableName;
import com.sx.table.core.repository.NativeSqlFromEM;
import com.sx.table.core.repository.TableNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 展示所有的数据库表
 */
@RestController
public class TableNameController {
    @Autowired
    TableNameRepository tableNameRepository;

    @Autowired
    NativeSqlFromEM nativeSqlFromEM;


    @RequestMapping("tablename/test")
    public Object test() {
        List<TableName> list = tableNameRepository.quryTables();
        System.out.println(list);
        return list;
    }

    @RequestMapping("/showtables")
    public List<HashMap<String, String>> showTables(@Param("databaseName") String databaseName) {
        List<HashMap<String, String>> hashMaps = nativeSqlFromEM.showTables(databaseName);
        return hashMaps;
    }


}
