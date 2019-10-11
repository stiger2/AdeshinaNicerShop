package com.example.adeshinanicershop.model;

public class CardItem {
    private String description;
    private double price;
    private int quantity;
    private String image;
    private String itemName;
    private String subTotal;

    public CardItem(String description, double price, String background, String itemName, String subTotal, int quantity) {
        this.description = description;
        this.price = price;
        this.image = background;
        this.itemName = itemName;
        this.subTotal = subTotal;
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getItemName() {
        return itemName;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
