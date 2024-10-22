package com.alexvoneinzbern.jwt_demo.controllers;

import com.alexvoneinzbern.jwt_demo.dto.UserRegistrationDto;
import com.alexvoneinzbern.jwt_demo.entity.User;
import com.alexvoneinzbern.jwt_demo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto registrationDto) {
    try {
      User user = userService.createUser(registrationDto.getUsername(), registrationDto.getPassword(), registrationDto.getRoles());
      return ResponseEntity.ok("User registered successfully");
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
