package com.naprojects.shoppingcart.repository;

import com.naprojects.shoppingcart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    void deleteAllByCartId(Long id);
}
