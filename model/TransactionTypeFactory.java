package com.aurionpro.model;

public class TransactionTypeFactory {
    public static ITransactionType getTransactionType(int typeId) {
        if (typeId == 0) {
            return new AddStock();
        } else if (typeId == 1) {
            return new RemoveStock();
        }
        throw new IllegalArgumentException("Invalid transaction type ID: " + typeId);
    }
}
