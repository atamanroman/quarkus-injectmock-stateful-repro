package org.acme;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Greeter {
  public String sayHello() {
    return "Hello, stranger.";
  }
}
