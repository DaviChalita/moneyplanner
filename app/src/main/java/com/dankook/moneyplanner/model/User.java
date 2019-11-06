package com.dankook.moneyplanner.model;

public class User {
    private String id;
    private String email;
    private String name;
    private String balance;

    public User() {

    }

    public User(String id, String email, String name, String balance) {
        this.setId(id);
        this.setEmail(email);
        this.setName(name);
        this.setBalance(balance);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}