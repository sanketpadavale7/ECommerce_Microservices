package com.projects.order_service.IMPL;


import com.projects.order_service.CustomExceptions.CartIsEmptyException;
import com.projects.order_service.CustomExceptions.OrderNotFoundException;
import com.projects.order_service.DTO.OrderItemsDTO;
import com.projects.order_service.DTO.OrderResponse;
import com.projects.order_service.Model.CartItem;
import com.projects.order_service.Model.OrderItems;
import com.projects.order_service.Model.OrderStatus;
import com.projects.order_service.Model.Orders;
import com.projects.order_service.Repository.CartRepo;
import com.projects.order_service.Repository.OrderRepo;
import com.projects.order_service.Service.CartService;
import com.projects.order_service.Service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceIMPL implements OrderService
{
    private final CartService cartService;
    private final OrderRepo oRepo;
    private final CartRepo cRepo;


    @Transactional
    @Override
    public ResponseEntity<OrderResponse> createOrder(Long userId)
    {
        List<CartItem> items= cRepo.findByUserId(userId);

        if(items.isEmpty())
        {
            throw new CartIsEmptyException("User Cart is empty");
        }
        /*if(userService.getUserById(userId) == null)
        {
            throw new UserNotFoundException("User Not Found");
        }*/


        // calculate total price

        BigDecimal totalAmount =items.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        Orders orders = new Orders();

        orders.setUserId(userId);
        orders.setTotalAmount(totalAmount);
        orders.setOrderStatus(OrderStatus.CONFIRMED);



        List<OrderItems> orderItems = items.stream()
                .map(item -> new OrderItems(null,item.getProductId(), item.getQuantity(), item.getPrice(), orders))
                .collect(Collectors.toList());

        orders.setItems(orderItems);

        Orders saveOrders = oRepo.save(orders);

        // Clear the cart after creating the order
        cartService.clearCart(userId);

        return new ResponseEntity<>(convertToOrderResponse(saveOrders), HttpStatus.CREATED);

    }



    private OrderResponse convertToOrderResponse(Orders saveOrders) {
        // Implementation for converting Orders to OrderResponse
        return new OrderResponse(
            saveOrders.getId(),
            saveOrders.getTotalAmount(),
            saveOrders.getItems().stream()
                .map(item -> new OrderItemsDTO(
                    item.getId(),
                    item.getProductId(),
                    item.getQuantity(),
                    item.getPrice()
                )).collect(Collectors.toList()),
            saveOrders.getOrderStatus()
        );
    }

    @Override
    public ResponseEntity<String> cancelOrder(Long userId, Long orderId)
    {
        /*if(!uRepo.existsById(userId))
        {
            throw new UserNotFoundException("User not found with id: " + userId);
        }*/
        if(!oRepo.existsById(Math.toIntExact(orderId)))
        {
            throw new OrderNotFoundException("Order not found with id: " + orderId);
        }

        Orders order = oRepo.findById(Math.toIntExact(orderId)).orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
        order.setOrderStatus(OrderStatus.CANCELLED);
        oRepo.save(order);
        return new ResponseEntity<>("Order canceled successfully", HttpStatus.OK);
    }
}
