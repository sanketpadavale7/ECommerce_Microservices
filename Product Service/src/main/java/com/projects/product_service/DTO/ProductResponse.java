package com.projects.product_service.DTO;

import com.projects.product_service.Model.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse
{
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private ProductStatus status;
}
