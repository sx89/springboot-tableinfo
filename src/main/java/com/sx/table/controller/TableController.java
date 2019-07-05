package com.sx.table.controller;

import com.sx.table.biz.ITableBiz;
import com.sx.table.common.Result;
import com.sx.table.common.TableFormat;
import com.sx.table.core.dao.nativesql.NativeSqlFromEM;
import com.sx.table.core.dao.jpa.TableFormatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    private static final Logger logger =  LoggerFactory.getLogger(TableController.class);
    @Autowired
    TableFormatRepository tableFormatRepository;

    @Autowired
    ITableBiz tableBiz;

    @Autowired
    NativeSqlFromEM nativeSqlFromEM;

    @RequestMapping("tableformat/test")
    @ResponseBody
    public Result showTableFormat(@RequestParam String tablename) {
        try {
            List<TableFormat> list = tableFormatRepository.queryTables(tablename);
            return new Result(list,true,"测试成功");
        } catch (Exception e) {
            logger.error("TableController[showTableFormat]",e);
            return new Result(false, "测试失败");
        }
    }

    @RequestMapping("tableformat/desctable(em)")
    @ResponseBody
    public Object descTable(@Param("tablename") String tablename) {
        try {
        List<HashMap<String, String>> tableFormatList = tableBiz.descTable(tablename);
        return new Result(tableFormatList,true,"获取字段信息成功");
    } catch (Exception e) {
        logger.error("TableController[showTableFormat]",e);
        return new Result(false, "获取字段信息失败");
    }
    }


    @RequestMapping("/showtables")
    @ResponseBody
    public Object showTables(@Param("databaseName") String databaseName) {
        try {
        List<HashMap<String, String>> hashMaps = tableBiz.showTables(databaseName);
            return new Result(hashMaps, true, "获取数据库表成功");
        } catch (Exception e) {
            logger.error("TableController[showTableFormat]",e);
            return new Result(false, "获取数据库表失败");
        }
    }

    @RequestMapping("/select*fromtable")
    public Result selectTable(@Param("tableName") String tableName) {
        try {
        List<HashMap<String, String>> tableData = tableBiz.selectTable(tableName);
        return new Result(tableData, true, "获取数据库表数据成功");
        } catch (Exception e) {
            logger.error("TableController[showTableFormat]",e);
            return new Result(false, "获取数据库表数据失败");
        }
    }

}
