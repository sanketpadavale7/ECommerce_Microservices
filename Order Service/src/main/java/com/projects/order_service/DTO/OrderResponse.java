package com.projects.order_service.DTO;


import com.projects.order_service.Model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse
{
    private Long Id;
    private BigDecimal totalAmount;
    private List<OrderItemsDTO> items;
    private OrderStatus status;

}
