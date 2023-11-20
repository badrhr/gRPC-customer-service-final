package com.customer.service.customerservice;

import com.customer.service.customerservice.entities.Customer;
import com.customer.service.customerservice.repository.CustomerRepository;
import com.customer.service.customerservice.web.CustomerRestControllerV1;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner start1(CustomerRepository customerRepository){
        return args -> {
            customerRepository.save(Customer.builder().name("x").email("x@gmail.com").build());
            customerRepository.save(Customer.builder().name("a").email("y@gmail.com").build());
            customerRepository.save(Customer.builder().name("b").email("z@gmail.com").build());
        };
    }

    @Bean
    CommandLineRunner start(CustomerRestControllerV1 customerRestControllerV1){
        return args -> {
            System.out.println("------------------------------//*");
            System.out.println( customerRestControllerV1.customerList());
        };
    }
}