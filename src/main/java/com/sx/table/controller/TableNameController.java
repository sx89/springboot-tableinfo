package com.sx.table.controller;

import com.dling.teamcore.common.model.TableName;
import com.dling.teamcore.core.repository.TableNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 展示所有的数据库表
 */
@RestController
@RequestMapping("datamodel/")
public class TableNameController {
    @Autowired
    TableNameRepository tableNameRepository;
    @RequestMapping("test")
    public void test() {
        List<TableName> list = tableNameRepository.quryTables();
        System.out.println(list);
    }
}
