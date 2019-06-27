package com.sx.table.core.repository;


import com.sx.table.common.TableFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TableFormatRepository extends JpaRepository<TableFormat, String> {

    @Query(value = "DESCRIBE teamcore_major_detail", nativeQuery = true)
    List<String[]> queryTables();

}
