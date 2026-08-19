package com.projects.order_service.CustomExceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String s)
    {
        super(s);
    }
}
