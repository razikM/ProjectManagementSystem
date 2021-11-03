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
        runConsoleApp();
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

}
