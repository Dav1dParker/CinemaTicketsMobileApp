package com.example.cinemaapp.movies;

public class Item2 {

    String name;
    String age;
    int image;
    int id;
    String Date;
    String Time;
    String NumberOfTickets;
    String Summ;

    public Item2(String name, String age, int image, int id, String Date, String Time, int NumberOfTickets, String Summ) {
        this.name = name;
        this.age = age;
        this.image = image;
        this.id = id;
        this.Date = Date;
        this.Time = Time;
        this.NumberOfTickets = Integer.toString(NumberOfTickets);
        this.Summ = Summ;
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

    public void setAge(String age) {
        this.age = age;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getNumberOfTickets() {
        return NumberOfTickets;
    }

    public void setNumberOfTickets(String numberOfTickets) {
        NumberOfTickets = numberOfTickets;
    }

    public String getSumm() {
        return Summ;
    }

    public void setSumm(String summ) {
        Summ = summ;
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
