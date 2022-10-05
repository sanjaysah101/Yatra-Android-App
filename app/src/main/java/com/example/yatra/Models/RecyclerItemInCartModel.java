package com.example.yatra.Models;

public class RecyclerItemInCartModel {
    private String title, deliveryDate, deliveryMode, img;
    private int price, quantity, id;

    public RecyclerItemInCartModel(String title, String deliveryDate, String deliveryMode, int price, String img, int quantity) {
        this.title = title;
        this.deliveryDate = deliveryDate;
        this.deliveryMode = deliveryMode;
        this.price = price;
        this.img = img;
        this.quantity = quantity;
    }

    public RecyclerItemInCartModel(int id,String title, String img, int price, int quantity) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeliveryDate() {
//        deliveryDate = "Delivery By "+deliveryDate;
        return new String("Delivery By " + deliveryDate);
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
