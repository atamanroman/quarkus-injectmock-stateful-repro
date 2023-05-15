package org.acme;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.*;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@QuarkusTest
class StatelessNestedTest {

  @InjectMock Greeter greeter;

  @BeforeEach
  void setup() {
    when(greeter.sayHello()).thenReturn("Hello, stateless test!");
  }

  @Nested
  @Order(0)
  class One {
    @Order(0)
    @Test
    void hello() {
      given().when().get("/hello").then().statusCode(200).body(is("Hello, stateless test!"));
    }

    @Order(1)
    @Test
    void helloAgain() {
      given().when().get("/hello").then().statusCode(200).body(is("Hello, stateless test!"));
    }
  }

  @Nested
  @Order(1)
  class Two {
    @Order(0)
    @Test
    void hello() {
      given().when().get("/hello").then().statusCode(200).body(is("Hello, stateless test!"));
    }

    @Order(1)
    @Test
    void helloAgain() {
      given().when().get("/hello").then().statusCode(200).body(is("Hello, stateless test!"));
    }
  }
}
