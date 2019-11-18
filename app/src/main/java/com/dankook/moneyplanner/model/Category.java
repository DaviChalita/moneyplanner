package com.dankook.moneyplanner.model;

public class Category {
    private String idCategory;
    private float amount;

    public Category() {

    }

    public Category(String idCategory, float amount) {
        this.setIdCategory(idCategory);
        this.setAmount(amount);
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
