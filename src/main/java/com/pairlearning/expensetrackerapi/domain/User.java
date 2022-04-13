package com.pairlearning.expensetrackerapi.domain;

public class User {
  private Integer userId;
  private String firstName;
  private String lastName;
  private String email;
  private String password;


 public User (Integer userId, String firstName, String lastName, String email, String password) {
  this.userId = userId;
  this.password = password;
  this.firstName = firstName;
  this.lastName = lastName;
  this.email = email;
 }

 public Integer getUserId() {
   return this.userId;
 }

public String getPassword() {
  return this.password;
}

public String getFirstName() {
  return this.firstName;
}

public String getEmail() {
  return this.email;
}

public Object getLastName() {
  return this.lastName;
}




  
}
