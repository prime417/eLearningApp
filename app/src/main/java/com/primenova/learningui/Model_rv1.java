package com.primenova.learningui;

import com.google.gson.annotations.SerializedName;

public class Model_rv1 {

    @SerializedName("title")
    private String title;
    @SerializedName("image")
    private String imageUrl;

    public Model_rv1(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
