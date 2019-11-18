package com.dankook.moneyplanner.model;

public class Account {
    private String id;
    private float balance;
    private User user;

    public Account(){

    }

    public Account(String id, float balance, User user){
        this.id = id;
        this.balance = balance;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
