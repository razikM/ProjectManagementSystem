package org.example.model;

import org.example.ConnectionHandler;

public class Project {

    private static int nextId = ConnectionHandler.initializeIdWithMaxValue("projects") + 1;

    private int id;
    private String name;
    private String description;

    public Project(String name, String description) {
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
