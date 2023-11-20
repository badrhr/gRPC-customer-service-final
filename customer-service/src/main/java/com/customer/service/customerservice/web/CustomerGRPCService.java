package com.customer.service.customerservice.web;

import com.customer.service.customerservice.entities.Customer;
import com.customer.service.customerservice.repository.CustomerRepository;
import com.customer.service.customerservice.stub.CustomerServiceGrpc;
import com.customer.service.customerservice.stub.CustomerServiceOuterClass;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import com.customer.service.customerservice.mapper.CustomerMapper;
import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class CustomerGRPCService extends CustomerServiceGrpc.CustomerServiceImplBase {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public void getAllCustomers(CustomerServiceOuterClass.GetAllCustomersRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomersResponse> responseObserver) {
        List<Customer> customers = customerRepository.findAll();
         List<CustomerServiceOuterClass.Customer> customersResponselist =
                 customers.stream()
                        .map(customerMapper::fromCustomer).collect(Collectors.toList());
        CustomerServiceOuterClass.GetCustomersResponse customersResponse= CustomerServiceOuterClass.GetCustomersResponse.newBuilder()
                .addAllCustomers(customersResponselist)
                .build();

        responseObserver.onNext(customersResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getCustomerById(CustomerServiceOuterClass.GetCustomerByIdRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomerByIdResponse> responseObserver) {
        Customer customerEntity=customerRepository.findById(request.getCustomerId()).get();
        CustomerServiceOuterClass.GetCustomerByIdResponse response=
                CustomerServiceOuterClass.GetCustomerByIdResponse.newBuilder()
                        .setCustomer(customerMapper.fromCustomer(customerEntity))
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void saveCustomer(CustomerServiceOuterClass.SaveCustomerRequest request, StreamObserver<CustomerServiceOuterClass.SaveCustomerResponse> responseObserver) {
        CustomerServiceOuterClass.CustomerRequest customerRequest = request.getCustomer();
        Customer customer=customerMapper.fromGrpcCustomerRequest(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerServiceOuterClass.SaveCustomerResponse response=
                CustomerServiceOuterClass.SaveCustomerResponse.newBuilder()
                        .setCustomer(customerMapper.fromCustomer(savedCustomer))
                        .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
