package com.dankook.moneyplanner.model;

public class Category {
    private String idCategory;
    private float total;
    private float food;
    private float shopping;
    private float leisure;
    private float transport;
    private float etc;

    public Category() {
    }

    public Category(String idCategory, float total, float food, float shopping, float leisure, float transport, float etc) {
        this.idCategory = idCategory;
        this.total = total;
        this.food = food;
        this.shopping = shopping;
        this.leisure = leisure;
        this.transport = transport;
        this.etc = etc;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = Float.parseFloat(food);
    }

    public float getShopping() {
        return shopping;
    }

    public void setShopping(String shopping) {
        this.shopping = Float.parseFloat(shopping);
    }

    public float getLeisure() {
        return leisure;
    }

    public void setLeisure(String leisure) {
        this.leisure = Float.parseFloat(leisure);
    }

    public float getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = Float.parseFloat(transport);
    }

    public float getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = Float.parseFloat(etc);
    }
}
