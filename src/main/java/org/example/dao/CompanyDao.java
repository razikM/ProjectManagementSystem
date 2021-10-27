package org.example.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.ConnectionHandler;
import org.example.model.Company;
import org.example.model.Project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompanyDao implements Dao<Integer, Company>{

    private static final Logger LOGGER = LogManager.getLogger(DeveloperDao.class);

    @Override
    public void create(Company entity) {
        try {
            ConnectionHandler.processVoidQuery("insert into companies(id, name, description)" +
                    " values(" + entity.getId() + ", " + entity.getName() + ", "
                    + entity.getDescription() + ")");
        } catch (SQLException throwables) {
            LOGGER.error("Could not create a company: " + entity.toString());
        }
    }

    @Override
    public Company get(Integer id) {
        try {
            Connection connection = ConnectionHandler.openConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from companies where id = " + id);

            resultSet.next();
            String name = resultSet.getString(2);
            String description = resultSet.getString(3);

            Company company = new Company(name,description);
            company.setId(id);

            ConnectionHandler.closeConnection(connection);

            return company;
        } catch (SQLException throwables) {
            LOGGER.error("Could not get a company with id " + id);
        }

        throw new RuntimeException("Could not get a company with id " + id);
    }

    @Override
    public void update(Company update) {
        try {
            ConnectionHandler.processVoidQuery("update companies set name = "
                    + update.getName() + ", description = " + update.getDescription() + " where id = " + update.getId());
        } catch (SQLException throwables) {
            LOGGER.error("Could not update a company: " + update.toString());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            ConnectionHandler.processVoidQuery("delete from companies where id = " + id);
        } catch (SQLException throwables) {
            LOGGER.error("Could not delete a company with id " + id);
        }
    }
}
