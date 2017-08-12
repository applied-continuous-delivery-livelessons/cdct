package com.example.customers;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
@EnableBinding(Source.class)
public class CustomerServiceApplication {

    @Bean
    ApplicationRunner init(CustomerRepository repository) {
        return args ->
                Stream.of("a", "b", "c")
                        .forEach(n -> repository.save(new Customer(n, n, n + "@" + n + ".com")));
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}

