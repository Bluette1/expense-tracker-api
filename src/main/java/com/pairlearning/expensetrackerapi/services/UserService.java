package com.pairlearning.expensetrackerapi.services;

import com.pairlearning.expensetrackerapi.domain.User;
import com.pairlearning.expensetrackerapi.exceptions.ETAuthException;

public interface UserService {

  User validateUser(String email, String password) throws ETAuthException;
  User registerUser(String firstName, String lastName, String email, String password) throws ETAuthException;

}
