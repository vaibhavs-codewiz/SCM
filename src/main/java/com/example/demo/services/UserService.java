package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.MyUser;

public interface UserService {
    MyUser saveUser(MyUser user);
    Optional<MyUser> getUserById(String id);
    Optional<MyUser> updateUser(MyUser user);
    void deleteUser(String id);
    boolean isUserExist(String id);
    boolean isUserExistByEmail(String email);
    List<MyUser> getAllUsers();
}
