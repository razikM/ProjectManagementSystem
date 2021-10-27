package org.example;

import org.example.config.DataSourceHolder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionHandler {

    public static int initializeIdWithMaxValue(String tableName){
        try (
                Connection connection = DataSourceHolder.getDataSource().getConnection();
                Statement statement = connection.createStatement()
            ) {

            ResultSet resultSet = statement.executeQuery("select max(id) from " + tableName + ";");
            resultSet.next();

            return resultSet.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        throw new RuntimeException("Could not initialize the id from the table " + tableName);
    }

    public static void processVoidQuery(String query) throws SQLException {
        Connection connection = DataSourceHolder.getDataSource().getConnection();
        connection.createStatement().executeQuery(query);
    }

    public static Connection openConnection() throws SQLException {
        return DataSourceHolder.getDataSource().getConnection();
    }

    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
