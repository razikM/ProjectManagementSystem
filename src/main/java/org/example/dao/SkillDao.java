package org.example.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.ConnectionHandler;
import org.example.model.Project;
import org.example.model.Skill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SkillDao implements Dao<Integer, Skill> {

    private static final Logger LOGGER = LogManager.getLogger(DeveloperDao.class);

    @Override
    public void create(Skill entity) {
        try {
            ConnectionHandler.processVoidQuery("insert into skills(id, name, level)" +
                    " values(" + entity.getId() + ", '" + entity.getName() + "', '"
                    + entity.getLevel() + "')");
        } catch (SQLException throwables) {
            LOGGER.error("Could not create a skill: " + entity.toString());
        }
    }

    @Override
    public Skill get(Integer id) {
        try {
            Connection connection = ConnectionHandler.openConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from skills where id = " + id);

            resultSet.next();
            String name = resultSet.getString(2);
            String level = resultSet.getString(3);

            Skill skill = new Skill(name,level);
            skill.setId(id);

            ConnectionHandler.closeConnection(connection);

            return skill;
        } catch (SQLException throwables) {
            LOGGER.error("Could not get a skill with id " + id);
        }

        throw new RuntimeException("Could not get a skill with id " + id);
    }

    @Override
    public void update(Skill update) {
        try {
            System.out.println("update skills set name = '"
                    + update.getName() + "', level = '" + update.getLevel() + "' where id = " + update.getId());
            ConnectionHandler.processVoidQuery("update skills set name = '"
                    + update.getName() + "', level = '" + update.getLevel() + "' where id = " + update.getId() + ";");

        } catch (SQLException throwables) {
            LOGGER.error("Could not update a skill: " + update.toString());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            ConnectionHandler.processVoidQuery("delete from skills where id = " + id);
        } catch (SQLException throwables) {
            LOGGER.error("Could not delete a skill with id " + id);
        }
    }
}
