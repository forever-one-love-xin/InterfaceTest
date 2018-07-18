package com.example.dell.interfacetest.datas;

public class Fruit {
    private String name;
    private int imageId;
    private String imageUrl;

    public Fruit(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
