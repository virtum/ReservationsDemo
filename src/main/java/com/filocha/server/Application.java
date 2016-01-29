package com.filocha.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("applicationContext.xml") // necessary for DI to work properly
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
