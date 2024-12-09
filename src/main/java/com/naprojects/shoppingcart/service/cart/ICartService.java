package com.naprojects.shoppingcart.service.cart;

import com.naprojects.shoppingcart.model.Cart;
import com.naprojects.shoppingcart.model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Cart initializeNewCart(User user);

    Cart getCartByUserId(Long userId);
}
