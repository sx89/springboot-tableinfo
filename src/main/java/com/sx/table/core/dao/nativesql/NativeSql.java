package com.sx.table.core.dao.nativesql;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class NativeSql {

    @Autowired
    protected DataSource dataSource;

//    原生jdbc
//    ResultSet rs = stmt.executeQuery("select * from emp");
//    ResultSetMetaData rsmd = rs.getMetaData();
//    System.out.println("No. of columns : " + rsmd.getColumnCount());
//    System.out.println("Column name of 1st column : " + rsmd.getColumnName(1));
//    System.out.println("Column type of 1st column : " + rsmd.getColumnTypeName(1));

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
