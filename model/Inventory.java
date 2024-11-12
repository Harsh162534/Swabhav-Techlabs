package com.aurionpro.model;

import com.aurionpro.exceptions.InsufficientStocksException;
import com.aurionpro.exceptions.InvalidProductIdException;
import com.aurionpro.exceptions.InvalidSupplierIdException;
import com.aurionpro.exceptions.DuplicateProductException;
import com.aurionpro.exceptions.DuplicateSupplierException;

import java.util.*;
import java.util.stream.Collectors;

public class Inventory {

    private Map<Integer, Product> products;
    private Map<Integer, Supplier> suppliers;
    private List<Transaction> transactions;

    public Inventory() {
        products = new HashMap<>();
        suppliers = new HashMap<>();
        transactions = new ArrayList<>();
    }

    // Product Management
    public void addProduct(Product product) throws DuplicateProductException {
        if (products.containsKey(product.getProduct_id())) {
            throw new DuplicateProductException("Product ID already exists.");
        }
        products.put(product.getProduct_id(), product);
    }

    public void updateProduct(int productId, String name, String description, int quantity, double price) throws InvalidProductIdException {
        Product existingProduct = products.get(productId);
        if (existingProduct == null) {
            throw new InvalidProductIdException("Product not found.");
        }
        existingProduct.setName(name);
        existingProduct.setDescription(description);
        existingProduct.setQuantity(quantity);
        existingProduct.setPrice(price);
    }

    public void deleteProduct(int productId) throws InvalidProductIdException {
        if (!products.containsKey(productId)) {
            throw new InvalidProductIdException("Product not found.");
        }
        products.remove(productId);
    }

    public Product viewProduct(int productId) throws InvalidProductIdException {
        Product product = products.get(productId);
        if (product == null) {
            throw new InvalidProductIdException("Product not found.");
        }
        return product;
    }

    public List<Product> viewAllProducts() {
        return new ArrayList<>(products.values());
    }

    // Supplier Management
    public void addSupplier(Supplier supplier) throws DuplicateSupplierException {
        if (suppliers.containsKey(supplier.getSupplier_id())) {
            throw new DuplicateSupplierException("Supplier ID already exists.");
        }
        suppliers.put(supplier.getSupplier_id(), supplier);
    }

    public void updateSupplier(int supplierId, String name, String contactInfo) throws InvalidSupplierIdException {
        Supplier existingSupplier = suppliers.get(supplierId);
        if (existingSupplier == null) {
            throw new InvalidSupplierIdException("Supplier not found.");
        }
        existingSupplier.setName(name);
        existingSupplier.setContactInfo(contactInfo);
    }

    public void deleteSupplier(int supplierId) throws InvalidSupplierIdException {
        if (!suppliers.containsKey(supplierId)) {
            throw new InvalidSupplierIdException("Supplier not found.");
        }
        suppliers.remove(supplierId);
    }

    public Supplier viewSupplierDetails(int supplierId) throws InvalidSupplierIdException {
        Supplier supplier = suppliers.get(supplierId);
        if (supplier == null) {
            throw new InvalidSupplierIdException("Supplier not found.");
        }
        return supplier;
    }

    public List<Supplier> viewAllSuppliers() {
        return new ArrayList<>(suppliers.values());
    }

    // Transaction Management
    public void processTransaction(int productId, ITransactionType transactionType, int quantity) throws InsufficientStocksException, InvalidProductIdException {
        Product product = viewProduct(productId);
        transactionType.applyTransaction(product, quantity);

        Transaction transaction = new Transaction(generateTransactionId(), productId, transactionType, quantity, new Date());
        transactions.add(transaction);
    }

    public List<Transaction> viewTransactionHistory(int productId) throws InvalidProductIdException {
        if (!products.containsKey(productId)) {
            throw new InvalidProductIdException("Product not found.");
        }
        return transactions.stream()
                .filter(transaction -> transaction.getProduct_id() == productId)
                .collect(Collectors.toList());
    }

    private int generateTransactionId() {
        return transactions.size() + 1; // Simple way to generate a unique ID
    }

    public void saveData() {
        // Implement data saving logic here (e.g., to a file or database)
    }

    public void loadData() {
        // Implement data loading logic here (e.g., from a file or database)
    }
}
