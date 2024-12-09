package com.naprojects.shoppingcart.service.order;

import com.naprojects.shoppingcart.dto.OrderDto;
import com.naprojects.shoppingcart.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);

    OrderDto convertToDto(Order order);

    List<OrderDto> getUserOrders(Long userId);
}
