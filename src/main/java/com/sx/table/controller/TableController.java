package com.sx.table.controller;

import com.sx.table.biz.ITableBiz;
import com.sx.table.common.Result;
import com.sx.table.common.model.TableFormat;
import com.sx.table.core.dao.NativeSqlFromEM;
import com.sx.table.core.repository.TableFormatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
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


    @RequestMapping("/showtables")
    @ResponseBody
    public Object showTables(@Param("databaseName") String databaseName) {
        try {
            List<HashMap<String, String>> hashMaps = tableBiz.showTables(databaseName);
            return new Result(hashMaps, true, "获取数据库表成功");
        } catch (Exception e) {
            logger.error("TableController[showTableFormat]", e);
            return new Result(false, "获取数据库表失败");
        }
    }

    @RequestMapping("tableformat/desctable")
    @ResponseBody
    public Object descTable(@Param("tableName") String tablename) {
        try {
        List<HashMap<String, String>> tableFormatList = tableBiz.getAllColumn(tablename);
        return new Result(tableFormatList,true,"获取字段信息成功");
        } catch (Exception e) {
        logger.error("TableController[showTableFormat]",e);
        return new Result(false, "获取字段信息失败");
        }
    }

    @RequestMapping("/selectfromtable")
    @ResponseBody
    public Result selectTable(@Param("tableName") String tableName) {
        try {
            List<HashMap<String, String>> tableData = tableBiz.selectFromTable(tableName);
        return new Result(tableData, true, "获取数据库表数据成功");
        } catch (Exception e) {
            logger.error("TableController[showTableFormat]",e);
            return new Result(false, "获取数据库表数据失败");
        }


    }

    @RequestMapping("/searchTableLikeStr")
    @ResponseBody
    public Result searchTableLikeStr(@RequestParam("tableNameSubStr") String findStr) {
        try {
            List<HashMap<String, String>> hashMaps = tableBiz.showTables(null);
            Iterator<HashMap<String, String>> iterator = hashMaps.iterator();
            while (iterator.hasNext()) {
                HashMap<String, String> next = iterator.next();
                String tableName = next.get("tableName");
                if (!tableName.contains(findStr)) {
                    iterator.remove();

                }
            }
            return new Result(hashMaps, true, "模糊筛选成功");
        } catch (Exception e) {
            logger.error("获取模糊匹配数据库表失败",e);
            return new Result(false, "获取模糊匹配数据库表失败");
        }
    }



}


//    @ResponseBody
//    @RequestMapping("/createColumn")
//    public Result createColum(@Param("columnIfo") CrudColumnInfo columnInfo) {
//        try {
//            Boolean result = (Boolean)tableBiz.createColumn(columnInfo);
//            if (!result) {
//                return new Result(false, "创建字段失败");
//            } else {
//                List<HashMap<String, String>> allColumn = tableBiz.getAllColumn(columnInfo.getName());
//                return new Result(allColumn, true, "创建字段成功,返回更新字段");
//            }
//        } catch (MyException e) {
//            logger.error("createColumn error", e);
//            return new Result(e.getErrorCode(), false, e.getMessage());
//        }
//    }
//
//
//    //TODO 删除前端传入的值 有些可以为空
//    @ResponseBody
//    @RequestMapping("/deleteColumn")
//    public Result deleteColumn(@Param("columnIfo") CrudColumnInfo columnInfo) {
//        try {
//            Boolean result = (Boolean)tableBiz.deleteColumn(columnInfo);
//            if (!result) {
//                return new Result(false, "删除字段失败");
//            } else {
//                List<HashMap<String, String>> allColumn = tableBiz.getAllColumn(columnInfo.getName());
//                return new Result(allColumn, true, "删除字段成功,返回更新字段");
//            }
//        } catch (MyException e) {
//            logger.error("createColumn error", e);
//            return new Result(e.getErrorCode(), false, e.getMessage());
//        }
//    }
