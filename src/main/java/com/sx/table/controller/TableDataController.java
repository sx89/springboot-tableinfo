package com.sx.table.controller;

import com.sx.table.core.repository.TableFormatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableDataController {
    @Autowired
    TableFormatRepository tableFormatRepository;

    //TODO 使用biz功能还是jpa?

    @RequestMapping("/tableinfo/test)")
    public Object test() {
        return null;
    }
}
