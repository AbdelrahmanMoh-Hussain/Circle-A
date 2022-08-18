package com.example.circlea;

public class Categories {
    private String name;
    private String imageUrl;

    public Categories(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
