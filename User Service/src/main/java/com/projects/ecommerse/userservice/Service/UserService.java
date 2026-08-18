package com.projects.ecommerse.userservice.Service;


import com.projects.ecommerse.userservice.DTO.UserRequest;
import com.projects.ecommerse.userservice.DTO.UserResponse;
import com.projects.ecommerse.userservice.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService 
{
    ResponseEntity<String> addUser(User user);

    ResponseEntity<List<UserResponse>> getAllUsers();

    ResponseEntity<UserResponse> getUserById(Long id);

    ResponseEntity<String> updateUser(Long id, UserRequest user);

    ResponseEntity<String> deleteUser(Long id);
}
