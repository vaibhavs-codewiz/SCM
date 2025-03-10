package com.example.demo.entities;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class MyContact {
    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNum;
    private String address;
    private String picture;
    @Column(length = 1000)
    private String description;
    private String websiteLink;
    private String linkedInLink;
    private boolean favorite = false;   
    
    @ManyToOne
    private MyUser user;

     @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<MysocialLink> socialLink = new ArrayList<>();

}
