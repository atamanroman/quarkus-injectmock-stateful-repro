package org.acme;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@QuarkusTest
class ManualStatefulTest {

  Greeter greeter;

  @BeforeAll
  void setup() {
    QuarkusMock.installMockForType(greeter = mock(Greeter.class), Greeter.class);
    when(greeter.sayHello()).thenReturn("Hello, stateful test!");
  }

  @Order(0)
  @Test
  void hello() {
    given().when().get("/hello").then().statusCode(200).body(is("Hello, stateful test!"));
  }

  @Order(1)
  @Test
  void helloAgain() {
    given().when().get("/hello").then().statusCode(200).body(is("Hello, stateful test!"));
  }
}
