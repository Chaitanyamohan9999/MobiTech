package com.shopping.electronics.models;


import java.io.Serializable;

/**
 * This class is a model file for Products
 */
public class ProductDo implements Serializable {

//    public String productId = "123";
//    public String productName = "Xiomy Note4";
//    public String productCategory = "Mobiles";
//    public String description = "Veri good smart mobile in android";
//    public double price = 12999;
//    public int quantity = 5;
//    public int image = R.drawable.mobile_category;
//    public boolean isAddedToCart;
    public String productId;
    public String productName;
    public String productCategory;
    public String description;
    public String price;
    public String quantity ;
    public String image;
    public boolean isAddedToCart;

    public ProductDo() {
    }

    public ProductDo(String productId, String productName, String productCategory, String description, String price, String quantity, String image, boolean isAddedToCart) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.isAddedToCart = isAddedToCart;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAddedToCart() {
        return isAddedToCart;
    }

    public void setAddedToCart(boolean addedToCart) {
        isAddedToCart = addedToCart;
    }
}
