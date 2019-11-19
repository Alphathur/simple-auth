package com.example.simpleauth;

import com.example.simpleauth.model.User;
import com.example.simpleauth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SimpleAuthApplicationTests {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  void contextLoads() {
    User user = new User();
    user.setUsername("root");
    user.setPassword(passwordEncoder.encode("123456"));
    userRepository.save(user);
  }

}
