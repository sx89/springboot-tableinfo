package com.sx.table.controller;

import com.sx.table.biz.TableBiz;
import com.sx.table.biz.impl.TableBizImpl;
import com.sx.table.common.TableFormat;
import com.sx.table.core.dao.nativesql.NativeSqlFromEM;
import com.sx.table.core.dao.jpa.TableFormatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 展示数据表每个字段的格式和名称
 * 展示数据的表和数据
 *
 * @author sx
 */
@RestController
public class TableController {

    @Autowired
    TableFormatRepository tableFormatRepository;

    @Autowired
    TableBiz tableBiz;

    @Autowired
    NativeSqlFromEM nativeSqlFromEM;

    @RequestMapping("tableformat/test")
    public Object showTableFormat(@RequestParam String tablename) {
        List<TableFormat> list = tableFormatRepository.queryTables(tablename);
        return list;
    }

    @RequestMapping("tableformat/desctable(em)")
    public List<HashMap<String, String>> descTable(@Param("tablename") String tablename) {
        List<HashMap<String, String>> tableFormatList = tableBiz.descTable(tablename);
        return tableFormatList;
    }


    @RequestMapping("/showtables")
    public List<HashMap<String, String>> showTables(@Param("databaseName") String databaseName) {
        List<HashMap<String, String>> hashMaps = tableBiz.showTables(databaseName);
        return hashMaps;
    }

    @RequestMapping("/select*fromtable")
    public List<HashMap<String, String>> selectTable(@Param("tableName") String tableName) {
        List<HashMap<String, String>> tableData = tableBiz.selectTable(tableName);
        return tableData;
    }

}
