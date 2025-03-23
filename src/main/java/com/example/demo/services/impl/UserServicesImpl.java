
package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.MyUser;
import com.example.demo.helpers.AppConstants;
import com.example.demo.helpers.ResourceNotFoundException;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServicesImpl implements UserService  
{
    @Autowired
    private UserRepo userRepo;
    // (during spring security config)
    @Autowired
    private PasswordEncoder passwordEncoder;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public MyUser saveUser(MyUser user) {
        //user id have to generate
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        //password encode (during spring security config)
          user.setPassword(passwordEncoder.encode(user.getPassword()));
        //set user role
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        logger.info(user.getProvider().toString());
        return userRepo.save(user);
    }

    @Override
    public Optional<MyUser> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    @Transactional
    public Optional<MyUser> updateUser(MyUser user) {
        //fetch the user
       MyUser user2 = userRepo.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundException());
       //update user2 with user , user2 contains data of user from db
       //now update user2 with user data.
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setAbout(user.getAbout());
        user2.setPhoneNum(user.getPhoneNum());
       //save the user in db
      MyUser save = userRepo.save(user2);
      return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
       MyUser user2 = userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException());
       userRepo.delete(user2);
    }

    @Override
    public boolean isUserExist(String id) {
         MyUser user2 = userRepo.findById(id).orElse(null);
         return user2 != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        MyUser user = userRepo.findByEmail(email).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public List<MyUser> getAllUsers() {
        return userRepo.findAll();
    }
    
}
