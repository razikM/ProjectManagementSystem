package org.example.console.commands;

import org.example.console.Command;
import org.example.dao.CompanyDao;
import org.example.dao.CustomerDao;
import org.example.model.Company;
import org.example.model.Customer;
import org.example.model.Skill;

import java.util.Arrays;
import java.util.List;

public class CustomerCommand implements Command {

    CustomerDao dao = new CustomerDao();

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

        Customer customer = new Customer(parameters[0], Integer.parseInt(parameters[1]));
        dao.create(customer);
        System.out.println("The customer was successfully created.");
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

        Customer customer = new Customer(parameters[1], Integer.parseInt(parameters[2]));
        customer.setId(Integer.parseInt(parameters[0]));

        dao.update(customer);
        System.out.println("The customer was successfully updated.");
    }

    private void delete(String[] parameters){
        if(parameters.length != 1){
            printHelp();
            return;
        }

        dao.delete(Integer.parseInt(parameters[0]));
        System.out.println("The customer was successfully deleted.");
    }

    private void getAll() {
        List<Customer> result = dao.getAll();

        if(result.isEmpty()){
            System.out.println("There are no customers in the database");
            return;
        }

        result.forEach(System.out :: println);
    }

    private void printHelp(){
        System.out.println("[customer] usage:");
        System.out.println("[operation] [mandatory_parameters]");
        System.out.println("[create] [name priority]");
        System.out.println("[get] [id]");
        System.out.println("[update] [id new_name new_priority]");
        System.out.println("[delete] [id]");
        System.out.println("[getAll] - returns all Customers");
    }
}
