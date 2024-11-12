package com.aurionpro.handling;

import com.aurionpro.model.Product;
import com.aurionpro.model.Supplier;
import com.aurionpro.model.Transaction;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileHandling {

    public static void saveProducts(Map<String, Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\SWABHAV_TRAINING\\SWABHAV_TRAINING\\COREJAVA\\InventoryProjectManagement\\src\\products.txt"))) {
            for (Product product : products.values()) {
                writer.write(product.toString()); 
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving products: " + e.getMessage());
        }
    }

    public static void saveSuppliers(Map<String, Supplier> suppliers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\SWABHAV_TRAINING\\SWABHAV_TRAINING\\COREJAVA\\InventoryProjectManagement\\src\\suppliers.txt"))) {
            for (Supplier supplier : suppliers.values()) {
                writer.write(supplier.toString()); 
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving suppliers: " + e.getMessage());
        }
    }

    public static void saveTransactions(List<Transaction> transactions) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\SWABHAV_TRAINING\\SWABHAV_TRAINING\\COREJAVA\\InventoryProjectManagement\\src\\transactions.txt"))) {
            for (Transaction transaction : transactions) {
                writer.write(transaction.toString()); 
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    public static List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Product product = Product.parseProduct(line); 
                products.add(product);
            }
        } catch (IOException e) {
            System.out.println("Error loading products: " + e.getMessage());
        }
        return products;
    }

    public static List<Supplier> loadSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("suppliers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Supplier supplier = Supplier.parseSupplier(line);
                suppliers.add(supplier);
            }
        } catch (IOException e) {
            System.out.println("Error loading suppliers: " + e.getMessage());
        }
        return suppliers;
    }

    public static List<Transaction> loadTransactions() throws ParseException {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Transaction transaction = Transaction.parseTransaction(line); 
                transactions.add(transaction);
            }
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
        return transactions;
    }

    public static void saveData(Map<String, Product> products, Map<String, Supplier> suppliers, List<Transaction> transactions) {
        saveProducts(products);
        saveSuppliers(suppliers);
        saveTransactions(transactions);
        System.out.println("Data saved successfully.");
    }

    public static void loadData(Map<String, Product> products, Map<String, Supplier> suppliers, List<Transaction> transactions) {
        products.clear();
        suppliers.clear();
        transactions.clear();
        
        List<Product> loadedProducts = loadProducts();
        for (Product product : loadedProducts) {
            products.put(String.valueOf(product.getProduct_id()), product);
        }

        List<Supplier> loadedSuppliers = loadSuppliers();
        for (Supplier supplier : loadedSuppliers) {
            suppliers.put(String.valueOf(supplier.getSupplier_id()), supplier);
        }

        try {
            List<Transaction> loadedTransactions = loadTransactions();
            transactions.addAll(loadedTransactions);
        } catch (ParseException e) {
            System.out.println("Error parsing transactions: " + e.getMessage());
        }

        System.out.println("Data loaded successfully.");
    }
}
