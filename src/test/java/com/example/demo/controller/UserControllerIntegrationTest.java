package com.example.demo.controller;

import com.example.demo.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class UserControllerIntegrationTest {

  @LocalServerPort private int port;

  @Autowired protected TestRestTemplate testRestTemplate;

  @Container
  static final PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>("postgres:11.1")
          .withUsername("sa")
          .withPassword("password")
          .withInitScript("init_script.sql")
          .withDatabaseName("integration-tests-db");

  @DynamicPropertySource
  static void postgresqlProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
  }

  @Test
  void testGetUserById_successful() {
    ResponseEntity<User> response =
        this.testRestTemplate.getForEntity("http://localhost:" + port + "/users/1", User.class);

    Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
    Assertions.assertEquals("Turan", response.getBody().getName());
  }

  @Test
  void testCountByStatus_successful() {
    ResponseEntity<Long> response =
        this.testRestTemplate.getForEntity(
            "http://localhost:" + port + "/users/count-by-status/1", Long.class);

    Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
    Assertions.assertEquals(2, response.getBody());
  }
}
