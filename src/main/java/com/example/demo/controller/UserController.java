package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable Long userId) {
    return ResponseEntity.ok(userService.getUserById(userId));
  }

  @GetMapping("/count-by-status/{status}")
  public ResponseEntity<Long> countByStatus(@PathVariable Integer status) {
    return ResponseEntity.ok(userService.countByStatus(status));
  }
}
