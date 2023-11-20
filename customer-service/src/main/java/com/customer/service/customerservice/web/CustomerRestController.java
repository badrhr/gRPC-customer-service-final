package com.customer.service.customerservice.web;

import com.customer.service.customerservice.entities.Customer;
import com.customer.service.customerservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerRestController {

    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> customerList(){
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer customerById(@PathVariable Long id){
        return customerRepository.findById(id).get();
    }

    @PostMapping("/customers")
    public Customer saveCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
        return customer;
    }
}