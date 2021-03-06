package org.example.console.commands;

import org.example.console.Command;
import org.example.dao.CompanyDao;
import org.example.dao.SkillDao;
import org.example.model.Company;
import org.example.model.Developer;
import org.example.model.Skill;

import java.util.Arrays;
import java.util.List;

public class SkillCommand implements Command {

    SkillDao dao = new SkillDao();

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

    private void getAll() {
        List<Skill> result = dao.getAll();

        if(result.isEmpty()){
            System.out.println("There are no skills in the database");
            return;
        }

        result.forEach(System.out :: println);
    }

    private void create(String[] parameters){
        if(parameters.length != 2){
            printHelp();
            return;
        }

        Skill skill = new Skill(parameters[0], parameters[1]);
        dao.create(skill);
        System.out.println("The skill was successfully created.");
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

        Skill skill = new Skill(parameters[1], parameters[2]);
        skill.setId(Integer.parseInt(parameters[0]));

        dao.update(skill);
        System.out.println("The skill was successfully updated.");
    }

    private void delete(String[] parameters){
        if(parameters.length != 1){
            printHelp();
            return;
        }

        dao.delete(Integer.parseInt(parameters[0]));
        System.out.println("The skill was successfully deleted.");
    }

    private void printHelp(){
        System.out.println("[skill] usage:");
        System.out.println("[operation] [mandatory_parameters]");
        System.out.println("[create] [name level]");
        System.out.println("[get] [id]");
        System.out.println("[update] [id new_name new_level]");
        System.out.println("[delete] [id]");
        System.out.println("[getAll] - returns all skills");
    }
}
