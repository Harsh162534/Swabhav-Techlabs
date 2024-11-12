package com.aurionpro.model;

import com.aurionpro.exceptions.InsufficientStocksException;

public class AddStock implements ITransactionType {

    

    @Override
    public String toString() {
        return "AddStock";
    }

	@Override
	public void applyTransaction(Product product, int quantity) throws InsufficientStocksException {
    product.setQuantity(product.getQuantity() + quantity);
		
	}

}
