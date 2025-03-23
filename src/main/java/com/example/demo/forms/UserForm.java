package com.example.demo.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {
   @NotBlank(message = "Username is required.")
   @Size(min = 4, message = "Minimum 4 characters are required.")
   private String name;

   @NotBlank(message = "Email is required.")
   @Email(message = "Invalid email address.")
   private String email;

   @NotBlank(message = "Password is required.")
   @Size(min = 6, message = "Minimum 6 characters are required.")
   private String password;

   @NotBlank(message = "About is required.")
   private String about;

   @Size(min = 9, max = 12, message = "Invalid Phone Number.")
   private String phoneNumber;

}
