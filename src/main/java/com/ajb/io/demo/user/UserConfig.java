package com.ajb.io.demo.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {

  @Bean
  CommandLineRunner commandLineRunner(UserRepository repository) {
    return args -> {
      User Ashley =
          new User(
              "Ashley Brown",
              "ashley1212@live.co.uk",
              "password",
              LocalDate.of(1994, Month.DECEMBER, 31));

      User Richard =
          new User(
              "Richard Jones",
              "richard1212@live.co.uk",
              "dickhead",
              LocalDate.of(1995, Month.APRIL, 28));

      repository.saveAll(List.of(Ashley, Richard));
    };
  }
}
