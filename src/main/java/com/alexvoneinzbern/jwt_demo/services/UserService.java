package com.alexvoneinzbern.jwt_demo.services;

import com.alexvoneinzbern.jwt_demo.entity.User;
import com.alexvoneinzbern.jwt_demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User createUser(String username, String password, Set<String> roles) {
    if (userRepository.findByUsername(username).isPresent()) {
      throw new RuntimeException("Username already exists");
    }

    User user = new User();
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
    user.setAuthorities(roles);

    return userRepository.save(user);
  }
}
