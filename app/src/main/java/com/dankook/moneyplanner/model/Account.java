package com.dankook.moneyplanner.model;

import java.io.Serializable;

public class Account implements Serializable {
    private String id;
    private float balance;
    private float spend;
    private User user;

    public Account() {

    }

    public Account(String id, float balance, float spend, User user) {
        this.id = id;
        this.balance = balance;
        this.spend = spend;
        this.user = user;
    }

    public float getSpend() {
        return spend;
    }

    public void setSpend(String spend) {
        this.spend = Float.parseFloat(spend);
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

    public float spend(float spendValue) {
        this.balance -= spendValue;
        return this.balance;
    }

    public float income(float incomeValue) {
        this.balance = incomeValue;
        return this.balance;
    }
}
