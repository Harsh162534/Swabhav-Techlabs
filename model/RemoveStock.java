package com.aurionpro.model;

import com.aurionpro.exceptions.InsufficientStocksException;

public class RemoveStock implements ITransactionType {

	
	  @Override
	    public String toString() {
	        return "RemoveStock";
	    }
	@Override
	public void applyTransaction(Product product, int quantity) throws InsufficientStocksException {
		if (product.getQuantity() < quantity) {
            throw new InsufficientStocksException("Not enough stock to remove.");
        }
        product.setQuantity(product.getQuantity() - quantity);
    }

}
