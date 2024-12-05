package com.naprojects.shoppingcart.exceptions;

import com.naprojects.shoppingcart.model.Product;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String s) {
        super(s);
    }
}
