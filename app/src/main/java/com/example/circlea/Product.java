package com.example.circlea;

import java.util.ArrayList;

public class Product {
    private String name;
    private String imaageUrl;
    private String price;
    private String category;
    public static ArrayList<Product> products;

    public Product(String name, String imaageUrl, String price, String category) {
        this.name = name;
        this.imaageUrl = imaageUrl;
        this.price = price;
        this.category = category;
    }
    public Product() {

    }

    public String getName() {
        return name;
    }

    public String getImaageUrl() {
        return imaageUrl;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    /*public static ArrayList<Product> getProducts() {
        return products;
    }

    public static void setProducts(ArrayList<Product> products) {
        Product.products = products;
    }*/
}
