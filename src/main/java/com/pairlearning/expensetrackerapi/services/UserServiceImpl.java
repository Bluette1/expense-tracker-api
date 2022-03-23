package com.pairlearning.expensetrackerapi.services;

import java.util.regex.Pattern;

import javax.swing.border.EtchedBorder;

import com.pairlearning.expensetrackerapi.domain.User;
import com.pairlearning.expensetrackerapi.exceptions.ETAuthException;
import com.pairlearning.expensetrackerapi.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
  @Autowired
  UserRepository userRepository;

  @Override
  public User validateUser(String email, String password) throws ETAuthException {
    return null;
  }

  @Override
  public User registerUser(String firstName, String lastName, String email, String password) throws ETAuthException {
    Pattern pattern = Pattern.compile("^(.+)@(.+)$");
    if (email != null) {
      email = email.toLowerCase();
    }
    if (!pattern.matcher(email).matches()) {
      throw new ETAuthException("Invalid email format");
    }
    Integer count = userRepository.getCountByEmail(email);
    if (count > 0) {
      throw new ETAuthException("Email already taken");
    }
    Integer userId = userRepository.create(firstName, lastName, email, password);
    System.out.println("userId:::::::::::" + userId);
    return userRepository.findById(userId);
  }
}
