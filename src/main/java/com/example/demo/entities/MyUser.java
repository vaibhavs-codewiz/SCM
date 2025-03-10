package com.example.demo.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity(name="user")
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyUser {
    @Id
   private String userId;
   @Column(name="userName", nullable = false)
   private String name;
   @Column(unique = true,nullable = false)
   private String email;
   @Column(length = 1000)
   private String about;
   @Column(length = 1000)
   private String profilePic;
   private String phoneNum;
   
//    information
   private boolean enabled = false;
   private boolean emailVerified = false;
   private boolean phoneVerified = false;

//    providers
   private Providers provider = Providers.SELF;
   private String providerUserId;

//    more fields- mapping
   @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
   private List<MyContact> contact = new ArrayList<>();

}
