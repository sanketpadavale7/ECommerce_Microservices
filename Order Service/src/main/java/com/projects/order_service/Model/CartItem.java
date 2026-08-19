package com.projects.order_service.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.User;

import java.math.BigDecimal;

@Data
@Entity
public class CartItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   private Long userId;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;

}
