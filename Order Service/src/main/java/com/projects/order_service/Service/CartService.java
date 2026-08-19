package com.projects.order_service.Service;


import com.projects.order_service.DTO.CartItemRequest;
import com.projects.order_service.DTO.CartItemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    ResponseEntity<String> addTocart(Long userId, CartItemRequest cartItemRequest);

    ResponseEntity<String> deleteItemFromCart(Long userId, Long productId);

    ResponseEntity<List<CartItemResponse>> getAllItemsInCart(Long userId);

    ResponseEntity<String> clearCart(Long userId);
}
