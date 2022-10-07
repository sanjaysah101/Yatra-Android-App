package com.example.yatra.Models;

public class RecyclerFavoriteItemModel {
    private String title, img;
    private int price, id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RecyclerFavoriteItemModel(int id, String title, String img, int price) {
        this.title = title;
        this.img = img;
        this.price = price;
        this.id = id;
    }
}
