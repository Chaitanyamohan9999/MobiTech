package com.example.mobitech;

import java.io.Serializable;

public class ProductDo implements Serializable {

    public String productId = "123";
    public String productName = "Xiomy Note4";
    public String productCategory = "Mobiles";
    public String description = "Veri good smart mobile in android";
    public double price = 12999;
    public int quantity = 5;
    public int image = R.drawable.mobile_category;
    public boolean isAddedToCart;
}
