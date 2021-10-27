package org.example.console;

import org.example.console.commands.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    Map<String, Command> commandMap = new HashMap<>();

    public CommandHandler() {
        commandMap.put("developer", new DeveloperCommand());
        commandMap.put("customer", new CustomerCommand());
        commandMap.put("company", new CompanyCommand());
        commandMap.put("project", new ProjectCommand());
        commandMap.put("skill", new SkillCommand());
    }

    public void handleCommand(String parameters) {
        String[] commands = parameters.split(" ");

        Command command = commandMap.get(commands[0]);

        if(command == null){
            printHelp();
            return;
        } else {
            command.handle(Arrays.copyOfRange(commands, 1, commands.length));
        }
    }

    private void printHelp(){
        System.out.println("Usage: [entity_name] [operation] [parameters]");
        System.out.println("Supported entities: [developer|company|customer|project|skill]");
        System.out.println("Supported operations: [create|get|update|delete]");
    }
}
