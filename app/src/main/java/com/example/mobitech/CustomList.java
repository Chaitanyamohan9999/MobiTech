package com.example.mobitech;

import android.graphics.Bitmap;

public class CustomList {

    public String name;
    public int price;
    public String des;
    public Bitmap image;

    public CustomList(String name, int price, String des) {
        this.name = name;
        this.price = price;
        this.des = des;
    }

    public CustomList(String name, int price, String des, Bitmap image) {
        this.name = name;
        this.price = price;
        this.des = des;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
