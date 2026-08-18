package com.projects.ecommerse.userservice.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Component
@Data
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fName;
    private String lName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole=UserRole.USER;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private List<Address> addresses;

}
