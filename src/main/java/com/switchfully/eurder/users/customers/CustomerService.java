package com.switchfully.eurder.users.customers;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerMapping customerMapping;

    public CustomerService(CustomerRepository customerRepository, CustomerMapping customerMapping) {
        this.customerRepository = customerRepository;
        this.customerMapping = customerMapping;
    }

    public CustomerDto createCustomer(CreateCustomerDto createCustomerDto) {
        Customer newCustomer = customerMapping.toCustomer(createCustomerDto);
        Customer savedCustomer = customerRepository.saveCustomer(newCustomer);
        return customerMapping.toCustomerDto(savedCustomer);
    }
}
