package org.example.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.App;
import org.example.ConnectionHandler;
import org.example.model.Developer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeveloperDao implements Dao<Integer, Developer> {

    private static final Logger LOGGER = LogManager.getLogger(DeveloperDao.class);

    @Override
    public void create(Developer entity) {
        try {
            ConnectionHandler.processVoidQuery("insert into developers(id, name, age, gender, salary)" +
                    " values(" + entity.getId() + ", '" + entity.getName() + "', "
                               + entity.getAge() + ", '" + entity.getGender() + "', " + entity.getSalary() + ");");
        } catch (SQLException throwables) {
            LOGGER.error("Could not create a developer: " + entity.toString());
        }
    }

    @Override
    public Developer get(Integer id) {
        try {
            Connection connection = ConnectionHandler.openConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from developers where id = " + id);

            resultSet.next();
            String name = resultSet.getString(2);
            int age = resultSet.getInt(3);
            String gender = resultSet.getString(4);
            int salary = resultSet.getInt(5);

            Developer developer = new Developer(name,age,gender,salary);
            developer.setId(id);

            ConnectionHandler.closeConnection(connection);

            return developer;
        } catch (SQLException throwables) {
            LOGGER.error("Could not get a developer with id " + id);
        }

        throw new RuntimeException("Could not get a developer with id " + id);
    }

    @Override
    public void update(Developer update) {
        try {
            ConnectionHandler.processVoidQuery("update developers set name = '"
                    + update.getName() + "', age = " + update.getAge() + ", gender = '"
                    + update.getGender() + "', salary = " + update.getSalary() + " where id = " + update.getId() + ";");
        } catch (SQLException throwables) {
            LOGGER.error("Could not update a developer: " + update.toString());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            ConnectionHandler.processVoidQuery("delete from developers where id = " + id + ";");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            LOGGER.error("Could not delete a developer with id " + id);
        }
    }
}
