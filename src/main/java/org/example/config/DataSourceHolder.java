package org.example.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.DataSource;
import java.util.Properties;

public class DataSourceHolder {

    private static DataSourceHolder value;

    private final DataSource dataSource;

    private DataSourceHolder() {
        Properties props = AppProperties.getProperties();
        MysqlDataSource dataSource = init(props);
        this.dataSource = dataSource;
    }

    private MysqlDataSource init(Properties props) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName(props.getProperty("db.host"));
        dataSource.setPort(Integer.parseInt(props.getProperty("db.port")));
        dataSource.setDatabaseName(props.getProperty("db.databaseName"));
        dataSource.setUser(props.getProperty("db.username"));
        dataSource.setPassword(props.getProperty("db.password"));
        return dataSource;
    }

    public static DataSource getDataSource() {
        if (value == null) {
            value = new DataSourceHolder();
        }
        return value.dataSource;
    }
}
