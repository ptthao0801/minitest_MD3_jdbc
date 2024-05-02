package org.example.minitestjdbc.model;

public class Book {
    protected int id;
    protected String name;
    protected String author;
    protected double price;
    protected String img;

    public Book(String name, String author, double price, String img) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.img = img;
    }

    public Book(int id, String name, String author, double price, String img) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
