package org.example.console.commands;

import org.example.console.Command;
import org.example.dao.DeveloperDao;
import org.example.model.Developer;

import java.util.Arrays;
import java.util.List;

public class DeveloperCommand implements Command {

    DeveloperDao dao = new DeveloperDao();

    @Override
    public void handle(String[] parameters) {
        if(parameters.length < 1){
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

            case "salaries": salaries(param);
                break;

            case "getAll": getAll();
                break;

            case "list": list(param);
                break;

            case "java": java();
                break;

            case "middle": middle();
                break;
        }
    }

    private void middle() {
        dao.listOfAllMiddleDevelopers();
    }

    private void java() {
        dao.listOfAllJavaDevelopers();
    }

    private void list(String[] parameters) {
        if(parameters.length != 1){
            printHelp();
            return;
        }

        dao.listOfDevelopersFromAProject(parameters[0]);
    }

    private void salaries(String[] parameters) {
        if(parameters.length != 1){
            printHelp();
            return;
        }

        dao.sumOfSalariesFromAProject(parameters[0]);
    }

    private void getAll() {
        List<Developer> result = dao.getAll();
        if(result.isEmpty()){
            System.out.println("There are no developers in the database");
            return;
        }
        result.forEach(System.out :: println);
    }

    private void create(String[] parameters){
        if(parameters.length != 4){
            printHelp();
            return;
        }

        Developer developer = new Developer(parameters[0], Integer.parseInt(parameters[1]),
                                            parameters[2],Integer.parseInt(parameters[3]));
        dao.create(developer);
        System.out.println("A developer was successfully created.");
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
        System.out.println("A developer was successfully updated.");
    }

    private void delete(String[] parameters){
        if(parameters.length != 1){
            printHelp();
            return;
        }

        dao.delete(Integer.parseInt(parameters[0]));
        System.out.println("A developer was successfully deleted.");
    }

    private void printHelp(){
        System.out.println("[developer] usage:");
        System.out.println("[operation] [mandatory_parameters]");
        System.out.println("[create] [name age gender salary]");
        System.out.println("[get] [id]");
        System.out.println("[update] [id new_name new_age new_gender salary]");
        System.out.println("[delete] [id]");
        System.out.println("Some extra commands:");
        System.out.println("usage:");
        System.out.println("[developer] [command_name] [parameters...]");
        System.out.println("[salaries] [project_name]" +
                " - returns a sum of salaries of developers from a project with project_name");
        System.out.println("[list] [project_name] - returns a list of developers from a project with project_name");
        System.out.println("[java] - returns a list of java developers from all projects");
        System.out.println("[middle] - returns a list of all middle developers");
        System.out.println("[getAll] - returns a list of all developers");
    }
}
