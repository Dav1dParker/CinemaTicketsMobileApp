package com.example.cinemaapp.movies;

public class Item {

    String name;
    String age;
    int image;
    int id;

    public Item(String name, String age, int image, int id) {
        this.name = name;
        this.age = age;
        this.image = image;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setEmail(String email) {
        this.age = email;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
