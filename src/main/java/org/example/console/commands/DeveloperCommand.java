package org.example.console.commands;

import org.example.console.Command;
import org.example.dao.DeveloperDao;
import org.example.model.Developer;

import java.util.Arrays;

public class DeveloperCommand implements Command {

    DeveloperDao dao = new DeveloperDao();

    @Override
    public void handle(String[] parameters) {
        if(parameters.length < 2){
            printHelp();
            return;
        }

        String[] param = Arrays.copyOfRange(parameters, 1, parameters.length);

        switch (parameters[0]){
            case "create": create(param);
                break;

            case "get": get(param);
                break;

            case "update": update(param);
                break;

            case "delete": delete(param);
                break;
        }
    }

    private void create(String[] parameters){
        if(parameters.length != 4){
            printHelp();
            return;
        }

        Developer developer = new Developer(parameters[0], Integer.parseInt(parameters[1]),
                                            parameters[2],Integer.parseInt(parameters[3]));
        dao.create(developer);
    }

    private void get(String[] parameters){
        if(parameters.length != 1){
            printHelp();
            return;
        }

        System.out.println(dao.get(Integer.parseInt(parameters[0])));
    }

    private void update(String[] parameters){
        if(parameters.length != 5){
            printHelp();
            return;
        }

        Developer developer = new Developer(parameters[1], Integer.parseInt(parameters[2]),
                                            parameters[3], Integer.parseInt(parameters[4]));
        developer.setId(Integer.parseInt(parameters[0]));

        dao.update(developer);
    }

    private void delete(String[] parameters){
        if(parameters.length != 1){
            printHelp();
            return;
        }

        dao.delete(Integer.parseInt(parameters[0]));
    }

    private void printHelp(){
        System.out.println("[developer] usage:");
        System.out.println("[operation] [mandatory_parameters]");
        System.out.println("[create] [name age gender salary]");
        System.out.println("[get] [id]");
        System.out.println("[update] [id new_name new_age new_gender salary]");
        System.out.println("[delete] [id]");
    }
}
