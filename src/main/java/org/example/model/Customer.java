package org.example.model;

import org.example.ConnectionHandler;

public class Customer {

    private static int nextId = ConnectionHandler.initializeIdWithMaxValue("customers") + 1;

    private int id;
    private String name;
    private int priority;

    public Customer(String name, int priority) {
        this.id = nextId;
        nextId++;
        this.name = name;
        this.priority = priority;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }
}
