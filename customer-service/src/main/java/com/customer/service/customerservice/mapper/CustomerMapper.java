package com.customer.service.customerservice.mapper;

import com.customer.service.customerservice.entities.Customer;
import com.customer.service.customerservice.stub.CustomerServiceOuterClass;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class CustomerMapper {
    private ModelMapper modelMapper=new ModelMapper();

    public CustomerServiceOuterClass.Customer fromCustomer(Customer customer){
        return modelMapper.map(customer, CustomerServiceOuterClass.Customer.Builder.class).build();
    }

    public Customer fromGrpcCustomerRequest(CustomerServiceOuterClass.CustomerRequest customerRequest){
        return modelMapper.map(customerRequest, Customer.class);
    }
}