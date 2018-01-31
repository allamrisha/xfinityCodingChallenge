package com.xfinity.model;

/**
 * Created by rashmi on 1/31/2018.
 */

public class Title {
    String title,description,image_url;

    public Title(String title, String description, String image_url) {
        this.title = title;
        this.description = description;
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_url() {
        return image_url;
    }
}
