package com.sx.table.core.repository;

import com.dling.teamcore.common.model.TableName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Odoo中的数据库模型的连接接口
 * @author sx
 */
public interface TableNameRepository extends JpaRepository<TableName,Object> {

    @Query(value="show tables",nativeQuery = true)
    List<TableName> quryTables();

}
