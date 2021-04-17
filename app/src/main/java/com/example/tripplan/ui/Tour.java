package com.example.tripplan.ui;

public class Tour {
    private String Name, Image, Description, Price, Discount, TourId;
    public Tour(){

    }

    public Tour(String name, String image, String description, String price, String discount, String tourId) {
        Name = name;
        Image = image;
        Description = description;
        Price = price;
        Discount = discount;
        TourId = tourId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getTourId() {
        return TourId;
    }

    public void setTourId(String tourId) {
        TourId = tourId;
    }
}
