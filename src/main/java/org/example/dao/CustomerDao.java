package org.example.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.ConnectionHandler;
import org.example.model.Customer;
import org.example.model.Project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDao implements Dao<Integer, Customer> {

    private static final Logger LOGGER = LogManager.getLogger(DeveloperDao.class);

    @Override
    public void create(Customer entity) {
        try {
            ConnectionHandler.processVoidQuery("insert into customers(id, name, priority)" +
                    " values(" + entity.getId() + ", '" + entity.getName() + "', "
                    + entity.getPriority() + ")");
        } catch (SQLException throwables) {
            LOGGER.error("Could not create a customer: " + entity.toString());
        }
    }

    @Override
    public Customer get(Integer id) {
        try {
            Connection connection = ConnectionHandler.openConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from customers where id = " + id);

            resultSet.next();
            String name = resultSet.getString(2);
            int priority = resultSet.getInt(3);

            Customer customer = new Customer(name,priority);
            customer.setId(id);

            ConnectionHandler.closeConnection(connection);

            return customer;
        } catch (SQLException throwables) {
            LOGGER.error("Could not get a customer with id " + id);
        }

        throw new RuntimeException("Could not get a customer with id " + id);
    }

    @Override
    public void update(Customer update) {
        try {
            ConnectionHandler.processVoidQuery("update customers set name = '"
                    + update.getName() + "', priority = " + update.getPriority() + " where id = " + update.getId());
        } catch (SQLException throwables) {
            LOGGER.error("Could not update a customer: " + update.toString());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            ConnectionHandler.processVoidQuery("delete from customers where id = " + id);
        } catch (SQLException throwables) {
            LOGGER.error("Could not delete a customer with id " + id);
        }
    }
}
