package org.example.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.ConnectionHandler;
import org.example.model.Developer;
import org.example.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao implements Dao<Integer, Project> {

    private static final Logger LOGGER = LogManager.getLogger(DeveloperDao.class);

    @Override
    public void create(Project entity) {
        try {
            ConnectionHandler.processVoidQuery("insert into projects(id, name, description, creation_date)" +
                    " values(" + entity.getId() + ", '" + entity.getName() + "', '"
                    + entity.getDescription() + "', " + entity.getDate() +");");
        } catch (SQLException throwables) {
            LOGGER.error("Could not create a project: " + entity.toString());
        }
    }

    @Override
    public Project get(Integer id) {
        try {
            Connection connection = ConnectionHandler.openConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from projects where id = " + id);

            resultSet.next();
            String name = resultSet.getString(2);
            String description = resultSet.getString(3);
            Date date = resultSet.getDate(4);

            Project project = new Project(name,description,date);
            project.setId(id);

            ConnectionHandler.closeConnection(connection);

            return project;
        } catch (SQLException throwables) {
            LOGGER.error("Could not get a project with id " + id);
        }

        throw new RuntimeException("Could not get a project with id " + id);
    }

    @Override
    public void update(Project update) {
        try {
            ConnectionHandler.processVoidQuery("update projects set name = '"
                    + update.getName() + "', description = '"
                    + update.getDescription() + "', creation_date = '" + update.getDate()
                    + "' where id = " + update.getId() + ";");
        } catch (SQLException throwables) {
            LOGGER.error("Could not update a project: " + update.toString());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            ConnectionHandler.processVoidQuery("delete from projects where id = " + id);
        } catch (SQLException throwables) {
            LOGGER.error("Could not delete a project with id " + id);
        }
    }

    @Override
    public List<Project> getAll() {
        try {
            Connection connection = ConnectionHandler.openConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from projects");

            List<Project> result = new ArrayList<>();

            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                Date date = resultSet.getDate(4);

                Project project = new Project(name, description);
                project.setId(id);
                project.setDate(date);

                result.add(project);
            }

            ConnectionHandler.closeConnection(connection);

            return result;
        } catch (SQLException throwables) {
            LOGGER.error("Could not get all projects");
        }

        throw new RuntimeException("Could not get all projects");
    }

    public void listOfProjects(){
        try {
            Connection connection = ConnectionHandler.openConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "select concat(creation_date, ' - ', temp.n, ' - ',temp.result) as output \n" +
                            "from (select projects.name as n, count(developer_id) as result from projects\n" +
                            "join developers_projects as ds on projects.id = ds.project_id\n" +
                            "join developers d on ds.developer_id = d.id\n" +
                            "group by projects.name) as temp\n" +
                            "join projects on temp.n = projects.name;");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }

            ConnectionHandler.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
