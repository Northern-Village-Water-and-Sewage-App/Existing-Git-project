package com.example.northernvillagewaterandsewageapp.ObjectClasses;

public class useItem {

    private String name;
    private int use;

    public useItem(String name, int use) {
        this.name = name;
        this.use = use;
    }

    public useItem(String name) {
        this.name = name;
    }

    public useItem(int use) {
        this.use = use;
    }

    public useItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUse() {
        return use;
    }

    public void setUse(int use) {
        this.use = use;
    }
}
