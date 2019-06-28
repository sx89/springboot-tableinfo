package com.sx.table.core.repository;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class NativeSql {

    @Autowired
    protected DataSource dataSource;

    public void showTables() throws Exception {
        DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
        ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            System.out.println(tableName);
            ResultSet columns = metaData.getColumns(null, null, tableName, "%");
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                System.out.println("\t" + columnName);
            }
        }
    }

}
