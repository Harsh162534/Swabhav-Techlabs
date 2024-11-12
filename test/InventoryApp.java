package com.aurionpro.test;

import com.aurionpro.model.*;
import com.aurionpro.exceptions.*;
import java.util.List;
import java.util.Scanner;

public class InventoryApp {

    private Inventory inventory;
    private Scanner scanner;

    public InventoryApp() {
        inventory = new Inventory();
        scanner = new Scanner(System.in);
    }

    public void displayMenu() throws InvalidProductIdException, InvalidSupplierIdException {
        while (true) {
            System.out.println("\n--- Inventory Management System ---");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View Product");
            System.out.println("5. View All Products");
            System.out.println("6. Add Supplier");
            System.out.println("7. Update Supplier");
            System.out.println("8. Delete Supplier");
            System.out.println("9. View Supplier Details");
            System.out.println("10. View All Suppliers");
            System.out.println("11. Process Transaction");
            System.out.println("12. View Transaction History");
            System.out.println("13. Save Data");
            System.out.println("14. Load Data");
            System.out.println("15. Generate Reports");
            System.out.println("16. Exit");
            
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  
            switch (choice) {
                case 1: addProduct(); break;
                case 2: updateProduct(); break;
                case 3: deleteProduct(); break;
                case 4: viewProduct(); break;
                case 5: viewAllProducts(); break;
                case 6: addSupplier(); break;
                case 7: updateSupplier(); break;
                case 8: deleteSupplier(); break;
                case 9: viewSupplier(); break;
                case 10: viewAllSuppliers(); break;
                case 11: processTransaction(); break;
                case 12: viewTransactionHistory(); break;
                case 13: saveData(); break;
                case 14: loadData(); break;
                case 15: generateReport(); break;
                case 16: exitApplication(); return;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addProduct() {
        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();

        Product product = new Product(id, name, description, quantity, price);
        try {
            inventory.addProduct(product);
            System.out.println("Product added successfully.");
        } catch (DuplicateProductException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateProduct() throws InvalidProductIdException {
        System.out.print("Enter product ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new product description: ");
        String description = scanner.nextLine();
        System.out.print("Enter new product quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter new product price: ");
        double price = scanner.nextDouble();

        inventory.updateProduct(id, name, description, quantity, price);
		System.out.println("Product updated successfully.");
    }

    private void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        int id = scanner.nextInt();
        try {
            inventory.deleteProduct(id);
            System.out.println("Product deleted successfully.");
        } catch (InvalidProductIdException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewProduct() {
        System.out.print("Enter product ID to view: ");
        int id = scanner.nextInt();
        try {
            Product product = inventory.viewProduct(id);
            System.out.println(product);
        } catch (InvalidProductIdException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewAllProducts() {
        List<Product> products = inventory.viewAllProducts();
        System.out.println("All Products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private void addSupplier() {
        System.out.print("Enter supplier ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  
        System.out.print("Enter supplier name: ");
        String name = scanner.nextLine();
        System.out.print("Enter supplier contact info: ");
        String contactInfo = scanner.nextLine();

        Supplier supplier = new Supplier(id, name, contactInfo);
        try {
            inventory.addSupplier(supplier);
            System.out.println("Supplier added successfully.");
        } catch (DuplicateSupplierException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateSupplier() throws InvalidSupplierIdException {
        System.out.print("Enter supplier ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter new supplier name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new supplier contact info: ");
        String contactInfo = scanner.nextLine();

        inventory.updateSupplier(id, name, contactInfo);
		System.out.println("Supplier updated successfully.");
    }

    private void deleteSupplier() {
        System.out.print("Enter supplier ID to delete: ");
        int id = scanner.nextInt();
        try {
            inventory.deleteSupplier(id);
            System.out.println("Supplier deleted successfully.");
        } catch (InvalidSupplierIdException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewSupplier() {
        System.out.print("Enter supplier ID to view: ");
        int id = scanner.nextInt();
        try {
            Supplier supplier = inventory.viewSupplierDetails(id);
            System.out.println(supplier);
        } catch (InvalidSupplierIdException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewAllSuppliers() {
        List<Supplier> suppliers = inventory.viewAllSuppliers();
        System.out.println("All Suppliers:");
        for (Supplier supplier : suppliers) {
            System.out.println(supplier);
        }
    }

    private void processTransaction() {
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter transaction type (1 for Add, 2 for Remove): ");
        int transactionTypeId = scanner.nextInt();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        ITransactionType transactionType;
        if (transactionTypeId == 1) {
            transactionType = new AddStock();
        } else if (transactionTypeId == 2) {
            transactionType = new RemoveStock();
        } else {
            System.out.println("Invalid transaction type.");
            return;
        }

        try {
            inventory.processTransaction(productId, transactionType, quantity);
            System.out.println("Transaction processed successfully.");
        } catch (InsufficientStocksException | InvalidProductIdException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewTransactionHistory() {
        System.out.print("Enter product ID to view transaction history: ");
        int productId = scanner.nextInt();
        try {
            List<Transaction> transactions = inventory.viewTransactionHistory(productId);
            System.out.println("Transaction History for Product ID " + productId + ":");
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        } catch (InvalidProductIdException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void generateReport() {
        List<Product> products = inventory.viewAllProducts();
        System.out.println("\n--- Product Report ---");
        System.out.printf( "ID", "Name", "Description", "Quantity", "Price");
        System.out.println("---------------------------------------------------------");
        for (Product product : products) {
            System.out.printf("%-10d %-20s %-30s %-10d %-10.2f%n", 
                              product.getProduct_id(), 
                              product.getName(), 
                              product.getDescription(), 
                              product.getQuantity(), 
                              product.getPrice());
        }
    }

    private void saveData() {
        try {
            inventory.saveData();
            System.out.println("Data saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private void loadData() {
        try {
            inventory.loadData();
            System.out.println("Data loaded successfully.");
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    private void exitApplication() {
        System.out.println("Exiting...");
        scanner.close();
    }
}
       
