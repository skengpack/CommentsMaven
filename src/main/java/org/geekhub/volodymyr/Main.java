package org.geekhub.volodymyr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.geekhub.volodymyr")
public class Main {
    public String PORT = System.getenv("PORT");
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
