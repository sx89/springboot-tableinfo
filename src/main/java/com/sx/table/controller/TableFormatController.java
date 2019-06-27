package com.sx.table.controller;

import com.sx.table.core.repository.TableFormatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 展示数据表每个字段的格式和名称
 * @author sx
 */
@RestController
public class TableFormatController {
    @Autowired
    TableFormatRepository tableFormatRepository;

    @RequestMapping("tableformat/test")
    public Object showTableFormat() {
        return tableFormatRepository.queryTables();
    }

}
