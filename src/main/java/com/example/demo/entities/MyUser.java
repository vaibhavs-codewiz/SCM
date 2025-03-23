package com.example.demo.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Version;


@Entity(name="user")
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
   @Getter(value=AccessLevel.NONE)
   private String Password;
//    information
   @Getter(value=AccessLevel.NONE)
   private boolean enabled = true;
   private boolean emailVerified = false;
   private boolean phoneVerified = false;
   
   @Version  // ✅ Hibernate uses this to prevent conflicts
private int version;

//    providers
   @Enumerated(value = EnumType.STRING)
   private Providers provider = Providers.SELF;
   private String providerUserId;
   
//    more fields- mapping
   @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
   private List<MyContact> contact = new ArrayList<>();

   @ElementCollection(fetch = FetchType.EAGER)
   private List<String> roleList = new ArrayList<>();
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
         
         Collection<SimpleGrantedAuthority> roles = roleList.stream()
         .map(role -> new SimpleGrantedAuthority(role))
         .collect(Collectors.toList());
         return roles;
   }

   @Override
   public String getPassword() {
      return this.Password;
   }

   //email as user name, we are using.
   @Override
   public String getUsername() {
      return this.email;
   }
   @Override
public boolean isAccountNonExpired() {
    return true;
}

@Override
public boolean isAccountNonLocked() {
    return true;
}

@Override
public boolean isCredentialsNonExpired() {
    return true;
}

@Override
public boolean isEnabled() {
    return this.enabled;
}


}
