package com.example.yatra.Models;

public class RecyclerCardProductsModel {
    private int img, price;
    private String title, unit, image, productId;
    Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public RecyclerCardProductsModel(int img, String title, int price, String unit) {
        this.img = img;
        this.price = price;
        this.title = title;
        this.unit = unit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public RecyclerCardProductsModel(int img, String title, int price){
        this.img = img;
        this.title = title;
        this.price = price;
    }
    public RecyclerCardProductsModel(Product product){
        this.image = product.getImage();
        this.title = product.getName();
        this.price = product.getPrice();
        this.unit = product.getUnit();
        this.product = product;
    }
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
