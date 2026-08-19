package com.projects.order_service.CustomExceptions;

public class CartIsEmptyException extends RuntimeException
{
    public CartIsEmptyException(String message)
    {
        super(message);
    }
}
