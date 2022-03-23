package com.pairlearning.expensetrackerapi.repositories;

import com.pairlearning.expensetrackerapi.domain.User;
import com.pairlearning.expensetrackerapi.exceptions.ETAuthException;

public interface UserRepository {
  Integer create(String firstName, String lastName, String email, String password)
  throws ETAuthException;
  User findByEmailAndPassword(String email, String password) throws ETAuthException;
  Integer getCountByEmail(String email);
  User findById(Integer userId);
}
