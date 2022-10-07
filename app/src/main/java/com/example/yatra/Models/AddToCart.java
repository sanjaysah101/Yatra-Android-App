package com.example.yatra.Models;

public class AddToCart {
    private int mQuantity;
    private Product mProduct;

    public AddToCart(Product product, int quantity) {
        this.mQuantity = quantity;
        this.mProduct = product;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public Product getProduct() {
        return mProduct;
    }

    public void setProduct(Product mProduct) {
        this.mProduct = mProduct;
    }
}
