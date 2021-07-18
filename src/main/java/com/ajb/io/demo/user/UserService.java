package com.ajb.io.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public void addNewUser(User user) {

    System.out.println(
        user); // doesn't show incoming json, instead it shows 'com.ajb.io.demo.user.User@14281765'

    Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
    if (userOptional.isPresent()) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "User already exists with that email.");
    }
    userRepository.save(user);
  }

  public void deleteUser(Long userId) {
    boolean exists = userRepository.existsById(userId);
    if (!exists) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user exists with that id.");
    }
    userRepository.deleteById(userId);
  }
}
