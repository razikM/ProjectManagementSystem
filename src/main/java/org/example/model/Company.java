package org.example.model;

import org.example.ConnectionHandler;

public class Company {

    private static int nextId = ConnectionHandler.initializeIdWithMaxValue("companies") + 1;

    private int id;
    private String name;
    private String description;

    public Company(String name, String description) {
        this.id = nextId;
        nextId++;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
