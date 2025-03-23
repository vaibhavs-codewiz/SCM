package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.MyUser;

@Repository
public interface UserRepo extends JpaRepository<MyUser,String> {
    //extra methods db related operations
    //custom query methods
    //custom finder methods
    
    Optional<MyUser> findByEmail(String Email);
}
