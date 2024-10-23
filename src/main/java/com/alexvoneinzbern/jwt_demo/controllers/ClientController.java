package com.alexvoneinzbern.jwt_demo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

  @GetMapping("/client")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public String Client(Principal principal) {
    return "Hello, " + principal.getName();
  }
}
