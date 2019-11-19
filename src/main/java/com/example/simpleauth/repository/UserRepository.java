package com.example.simpleauth.repository;

import com.example.simpleauth.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

  User getByUsername(String username);
}
