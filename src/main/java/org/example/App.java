package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.config.DbMigration;
import org.example.console.CommandHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
    private static final Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) throws SQLException {
        LOGGER.debug("Start application");
        DbMigration.migrate();
//        runConsoleApp();
        LOGGER.info("END application");
    }

    // Console app to work with database

    public static void runConsoleApp() {
        CommandHandler commandHandler = new CommandHandler();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            commandHandler.handleCommand(scanner.nextLine());
        }

        scanner.close();
    }

    // Schoology assignments

    private static void sumOfSalariesFromAProject(String projectName) throws SQLException {
        Connection connection = ConnectionHandler.openConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select sum(developers.salary) from developers\n" +
                " join developers_projects on developers.id = developers_projects.developer_id\n" +
                " join projects on developers_projects.project_id = projects.id\n" +
                " where projects.name = '" + projectName + "';");

        resultSet.next();

        System.out.println(resultSet.getInt(1));

        ConnectionHandler.closeConnection(connection);
    }

    private static void listOfDevelopersFromAProject(String projectName) throws SQLException {
        Connection connection = ConnectionHandler.openConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select developers.name from developers\n" +
                " join developers_projects on developers.id = developers_projects.developer_id\n" +
                " join projects on developers_projects.project_id = projects.id\n" +
                " where projects.name = '" + projectName + "';");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }

        ConnectionHandler.closeConnection(connection);
    }

    private static void listOfAllJavaDevelopers() throws SQLException {
        Connection connection = ConnectionHandler.openConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select developers.name from developers\n" +
                " join developers_skills as ds on developers.id = ds.developer_id \n" +
                " join skills on ds.skill_id = skills.id\n" +
                " where skills.name = 'Java';");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }

        ConnectionHandler.closeConnection(connection);
    }

    private static void listOfAllMiddleDevelopers() throws SQLException {
        Connection connection = ConnectionHandler.openConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select developers.name from developers\n" +
                " join developers_skills as ds on developers.id = ds.developer_id \n" +
                " join skills on ds.skill_id = skills.id\n" +
                " where skills.level = 'Middle';");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }

        ConnectionHandler.closeConnection(connection);
    }

    private static void listOfProjects() throws SQLException {
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
    }
}
