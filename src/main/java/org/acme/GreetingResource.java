package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Objects;

@Path("/hello")
public class GreetingResource {

  @Inject Greeter greeter;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    var message = greeter.sayHello();
    return Objects.requireNonNullElse(message, "Nobody said hello :(");
  }
}
