package com.example.retrofitappdemo;

public class NewsData {
    private String name;
    private String image;
    private String description;
    private String date;
    private String source;
    private String id;

    public NewsData(String name, String image, String description, String date, String source, String id) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.date = date;
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getSource() {
        return source;
    }

}

