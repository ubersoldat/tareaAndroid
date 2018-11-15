package com.example.felipe.tareaandroid;

public class Photo {

    private String title; //Titulo de la foto
    private String imageUrl; // URl de la foto

    public Photo(){    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
