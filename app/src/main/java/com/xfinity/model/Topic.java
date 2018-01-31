package com.xfinity.model;


public class Topic {

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE_URL = "img_url";

    String title;
    String description;
    String imageUrl;

    public Topic(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
