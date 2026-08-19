package com.projects.order_service.Service;


import com.projects.order_service.DTO.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderService
{

    ResponseEntity<OrderResponse> createOrder(Long userId);

    ResponseEntity<String> cancelOrder(Long userId, Long orderId);
}
