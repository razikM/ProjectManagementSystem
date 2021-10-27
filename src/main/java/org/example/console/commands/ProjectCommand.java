package org.example.console.commands;

import org.example.console.Command;
import org.example.dao.CompanyDao;
import org.example.dao.ProjectDao;
import org.example.model.Company;
import org.example.model.Project;

import java.util.Arrays;

public class ProjectCommand implements Command {

    ProjectDao dao = new ProjectDao();

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
        if(parameters.length != 2){
            printHelp();
            return;
        }

        Project project = new Project(parameters[0], parameters[1]);
        dao.create(project);
    }

    private void get(String[] parameters){
        if(parameters.length != 1){
            printHelp();
            return;
        }

        System.out.println(dao.get(Integer.parseInt(parameters[0])));
    }

    private void update(String[] parameters){
        if(parameters.length != 3){
            printHelp();
            return;
        }

        Project project = new Project(parameters[1], parameters[2]);
        project.setId(Integer.parseInt(parameters[0]));

        dao.update(project);
    }

    private void delete(String[] parameters){
        if(parameters.length != 1){
            printHelp();
            return;
        }

        dao.delete(Integer.parseInt(parameters[0]));
    }

    private void printHelp(){
        System.out.println("[project] usage:");
        System.out.println("[operation] [mandatory_parameters]");
        System.out.println("[create] [name description]");
        System.out.println("[get] [id]");
        System.out.println("[update] [id new_name new_description]");
        System.out.println("[delete] [id]");
    }
}
