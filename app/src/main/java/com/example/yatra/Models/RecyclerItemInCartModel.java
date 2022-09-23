package com.example.yatra.Models;

public class RecyclerItemInCartModel {
    private String title, deliveryDate, deliveryMode;
    private int price, img, quantity;

    public RecyclerItemInCartModel(String title, String deliveryDate, String deliveryMode, int price, int img, int quantity) {
        this.title = title;
        this.deliveryDate = deliveryDate;
        this.deliveryMode = deliveryMode;
        this.price = price;
        this.img = img;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeliveryDate() {
//        deliveryDate = "Delivery By "+deliveryDate;
        String s = new String("Delivery By " + deliveryDate);
        return s;
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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
