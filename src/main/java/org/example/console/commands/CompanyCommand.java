package org.example.console.commands;

import org.example.console.Command;
import org.example.dao.CompanyDao;
import org.example.dao.DeveloperDao;
import org.example.model.Company;
import org.example.model.Developer;
import org.example.model.Skill;

import java.util.Arrays;
import java.util.List;

public class CompanyCommand implements Command {

    CompanyDao dao = new CompanyDao();

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

            case "getAll": getAll();
                break;
        }
    }

    private void create(String[] parameters){
        if(parameters.length != 2){
            printHelp();
            return;
        }

        Company company = new Company(parameters[0], parameters[1]);
        dao.create(company);
        System.out.println("The company was successfully created.");
    }

    private void get(String[] parameters){
        if(parameters.length != 1){
            printHelp();
            return;
        }

        System.out.println(dao.get(Integer.parseInt(parameters[0])));
    }

    private void getAll() {
        List<Company> result = dao.getAll();

        if(result.isEmpty()){
            System.out.println("There are no companies in the database");
            return;
        }

        result.forEach(System.out :: println);
    }

    private void update(String[] parameters){
        if(parameters.length != 3){
            printHelp();
            return;
        }

        Company company = new Company(parameters[1], parameters[2]);
        company.setId(Integer.parseInt(parameters[0]));

        dao.update(company);
        System.out.println("The company was successfully updated.");
    }

    private void delete(String[] parameters){
        if(parameters.length != 1){
            printHelp();
            return;
        }

        dao.delete(Integer.parseInt(parameters[0]));
        System.out.println("The company was successfully deleted.");
    }

    private void printHelp(){
        System.out.println("[company] usage:");
        System.out.println("[operation] [mandatory_parameters]");
        System.out.println("[create] [name description]");
        System.out.println("[get] [id]");
        System.out.println("[update] [id new_name new_description]");
        System.out.println("[delete] [id]");
    }
}
