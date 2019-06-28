package com.sx.table.controller;

import com.sx.table.common.TableFormat;
import com.sx.table.core.repository.NativeSqlFromEM;
import com.sx.table.core.repository.TableFormatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 展示数据表每个字段的格式和名称
 *
 * @author sx
 */
@RestController
public class TableFormatController {

    @Autowired
    TableFormatRepository tableFormatRepository;

    @Autowired
    NativeSqlFromEM nativeSqlFromEM;

    @RequestMapping("tableformat/test")
    public Object showTableFormat(@RequestParam String tablename) {
        List<TableFormat> list = tableFormatRepository.queryTables(tablename);
        return list;
    }

    @RequestMapping("tableformat/desctable(em)")
    public List<HashMap<String, String>> showTablesEM(@Param("tablename") String tablename) {
        List<HashMap<String, String>> tableFormatList = nativeSqlFromEM.descTable(tablename);
        return tableFormatList;
    }

}
