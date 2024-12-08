package com.naprojects.shoppingcart.repository;

import com.naprojects.shoppingcart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
