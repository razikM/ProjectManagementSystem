package org.example.model;

import org.example.ConnectionHandler;

public class Skill {

    private static int nextId = ConnectionHandler.initializeIdWithMaxValue("skills") + 1;

    private int id;
    private String name;
    private String level;

    public Skill(String name, String level) {
        this.id = nextId;
        nextId++;
        this.name = name;
        this.level = level;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
