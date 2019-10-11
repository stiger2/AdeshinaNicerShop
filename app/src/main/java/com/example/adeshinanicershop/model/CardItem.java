package com.example.adeshinanicershop.model;

public class CardItem {
    private String description;
    private String price;
    private String quantity;
    private int image;
    private String itemName;
    private String subTotal;

    public CardItem(String description, String price, int background, String itemName, String subTotal, String quantity) {
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

    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public String getItemName() {
        return itemName;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public String getQuantity() {
        return quantity;
    }


    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public void setImage(int image) {
        this.image = image;
    }


}
