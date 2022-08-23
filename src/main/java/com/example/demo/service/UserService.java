package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  public User getUserById(Long userId) {
    return userRepository.findById(userId).orElseGet(null);
  }

  public Long countByStatus(Integer status) {
    return userRepository.countByStatus(status);
  }
}
