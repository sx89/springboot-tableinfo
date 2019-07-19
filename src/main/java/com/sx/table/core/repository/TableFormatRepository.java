package com.sx.table.core.repository;


import com.sx.table.common.model.TableFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TableFormatRepository extends JpaRepository<TableFormat, String> {

    /**
     * 这种方法会报 sql语法错误,请检查 ''tablename''  附近
     * 是因为 tablename的值在被填充进value的时候,会增加 一个 ''包裹起来
     * 这在普通的查询语句 比如 select * from tablename where name = '***';是没错的
     * 但在describe tablename ;这种语句中, describe 'tablename';是错误的格式
     * <p>
     * 新思路
     * nativesql
     * 实体类管理器
     *
     * @param tablename
     * @return
     */
    @Query(value = "describe `?1`", nativeQuery = true)
    List<TableFormat> queryTables(String tablename);

}
