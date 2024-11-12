package com.aurionpro.model;

import com.aurionpro.exceptions.InsufficientStocksException;

public interface ITransactionType {

    void applyTransaction(Product product, int quantity) throws InsufficientStocksException;

    // Constants for transaction types (optional)
    int ADD = 0;
    int REMOVE = 1;
}
