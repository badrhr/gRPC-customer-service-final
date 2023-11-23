# gRPC-customer-service-final


#in order to use the mapping function from the customer entity to ustomerServiceOuterClass.Customer we need to use the builder pattern as follows:


 public CustomerServiceOuterClass.Customer fromCustomer(Customer customer){
        return modelMapper.map(customer, CustomerServiceOuterClass.Customer.Builder.class).build();
    }
