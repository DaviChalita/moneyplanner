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

    public void setBalance(String balance) {
        this.balance = Float.parseFloat(balance);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float spend(float spendValue){
        this.balance -= spendValue;
        return this.balance;
    }

    public float income(float incomeValue){
        this.balance = incomeValue;
        return this.balance;
    }
}
