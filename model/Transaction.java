package com.aurionpro.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

    private int transaction_id;
    private int product_id;
    private ITransactionType transactionType;
    private int quantity;
    private Date date;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Transaction(int transaction_id, int product_id, ITransactionType transactionType, int quantity, Date date) {
        this.transaction_id = transaction_id;
        this.product_id = product_id;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.date = date;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public ITransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(ITransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static Transaction parseTransaction(String data) throws ParseException {
        String[] fields = data.split(",");

        int transaction_id = Integer.parseInt(fields[0].trim());
        int product_id = Integer.parseInt(fields[1].trim());
        int transactionTypeId = Integer.parseInt(fields[2].trim());
        int quantity = Integer.parseInt(fields[3].trim());
        Date date = dateFormat.parse(fields[4].trim());
        ITransactionType transactionType = TransactionTypeFactory.getTransactionType(transactionTypeId);

        return new Transaction(transaction_id, product_id, transactionType, quantity, date);
    }
}
