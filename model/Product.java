package com.aurionpro.model;

public class Product {

    private int product_id;
    private String name;
    private String description;
    private int quantity;
    private double price;

    public Product(int product_id, String name, String description, int quantity, double price) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [product_id=" + product_id + ", name=" + name + ", description=" + description + ", quantity=" + quantity + ", price=" + price + "]";
    }

    public static Product parseProduct(String line) {
        String[] parts = line.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid product data: " + line);
        }
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        String description = parts[2];
        int quantity = Integer.parseInt(parts[3]);
        double price = Double.parseDouble(parts[4]);
        return new Product(id, name, description, quantity, price);
    }
}
