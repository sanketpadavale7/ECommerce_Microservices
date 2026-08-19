package com.projects.order_service.IMPL;


import com.projects.order_service.DTO.CartItemRequest;
import com.projects.order_service.DTO.CartItemResponse;
import com.projects.order_service.Model.CartItem;
import com.projects.order_service.Repository.CartRepo;
import com.projects.order_service.Service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceIMPL implements CartService

{
    @Autowired
    private CartRepo cRepo;

//    @Autowired
//    private UserRepo uRepo;
//
//    @Autowired
//    private ProductRepo pRepo;

    @Override
    public ResponseEntity<String> addTocart(Long userId, CartItemRequest cartItemRequest)
    {
        /*if(!uRepo.existsById(userId))
        {
            throw new UserNotFoundException("User not found with id: " + userId);
        }

        if (!pRepo.existsById(Math.toIntExact(cartItemRequest.getProductId())))
        {
            throw new ProductNotFoundException("Product not found with id: " + cartItemRequest.getProductId());
        }
*/
        /*Product product =pRepo.findById(Math.toIntExact(cartItemRequest.getProductId())).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + cartItemRequest.getProductId()));

        if(product.getStockQuantity() < cartItemRequest.getQuantity())
        {
            return ResponseEntity.badRequest().body("Insufficient stock for product: " + product.getName());
        }*/

        CartItem alreadyExists = cRepo.findByUserIdAndProductId(userId, cartItemRequest.getProductId());

        if(alreadyExists != null)
        {
            alreadyExists.setQuantity(cartItemRequest.getQuantity() + alreadyExists.getQuantity());
            alreadyExists.setPrice(BigDecimal.valueOf(1000.00));
            cRepo.save(alreadyExists);

            //product.setStockQuantity(product.getStockQuantity() - alreadyExists.getQuantity());
            //pRepo.save(product);
            return ResponseEntity.ok("Item added to cart successfully");



             }


        else {



        //User user = uRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        CartItem cartItem = new CartItem();
        cartItem.setUserId(userId);
        cartItem.setQuantity(cartItemRequest.getQuantity());
        cartItem.setPrice(BigDecimal.valueOf(1000.00));
        cartItem.setProductId(cartItemRequest.getProductId());

            //product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
            //pRepo.save(product);

        cRepo.save(cartItem);

        return ResponseEntity.ok("Item added to cart successfully");
    }
    }

    @Override
    public ResponseEntity<String> deleteItemFromCart(Long userId, Long productId)
    {
        /*if(!uRepo.existsById(userId))
        {
            throw new UserNotFoundException("User not found with id: " + userId);
        }

        if (!pRepo.existsById(Math.toIntExact(productId)))
        {
            throw new ProductNotFoundException("Product not found with id: " + productId);
        }*/

        Optional<CartItem> item = Optional.ofNullable(cRepo.findByUserIdAndProductId(userId, productId));

        if (item.isPresent()) {
            cRepo.deleteById(Math.toIntExact(item.get().getId()));
        }
        return ResponseEntity.ok("Item deleted from cart successfully");
    }

    @Override
    public ResponseEntity<List<CartItemResponse>> getAllItemsInCart(Long userId)
    {
       /* if(!uRepo.existsById(userId))
        {
            throw new UserNotFoundException("User not found with id: " + userId);
        }*/
        List<CartItem> items = cRepo.findByUserId(userId);
        List<CartItemResponse> cartItemResponses = new ArrayList<>();
        for (CartItem item : items)
        {

            CartItemResponse response = new CartItemResponse();
            response.setQuantity(item.getQuantity());
            response.setTotalPrice(item.getPrice());
            cartItemResponses.add(response);
        }
        return new ResponseEntity<>(cartItemResponses, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<String> clearCart(Long userId)
    {


        cRepo.deleteByUserId(userId);

        return new ResponseEntity<>("Cart cleared successfully", HttpStatus.OK);
    }
}
