package com.aurionpro.model;

public class Supplier {

    private int supplier_id;
    private String name;
    private String contactInfo;

    public Supplier(int supplier_id, String name, String contactInfo) {
        this.supplier_id = supplier_id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public static Supplier parseSupplier(String data) {
        String[] fields = data.split(",");
        int id = Integer.parseInt(fields[0]);
        String name = fields[1];
        String contactInfo = fields[2];
        return new Supplier(id, name, contactInfo);
    }

    @Override
    public String toString() {
        return "Supplier [supplier_id=" + supplier_id + ", name=" + name + ", contactInfo=" + contactInfo + "]";
    }
}
