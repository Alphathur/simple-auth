package com.example.simpleauth.web;

import com.example.simpleauth.common.UserRole;
import com.example.simpleauth.common.auth.MyUserDetails;
import com.example.simpleauth.model.User;
import com.example.simpleauth.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository userRepository;

  @PreAuthorize("hasAnyAuthority('ROOT')")
  @RequestMapping("add")
  public User add(String userName) {
    User user = new User();
    user.setUsername(userName);
    user.setPassword(passwordEncoder.encode("123456"));
    user.setUserRole(UserRole.GUEST);
    userRepository.save(user);
    return user;
  }

  @PreAuthorize("hasAnyAuthority('ROOT','ADMIN')")
  @GetMapping("all")
  public List<User> all() {
    return StreamSupport.stream(userRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @GetMapping("login_user")
  public User current(Authentication authentication) {
    MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
    return myUserDetails.getUser();
  }

  @GetMapping("user_id")
  public Integer currentId(User user) {
    return user.getId();
  }
}
