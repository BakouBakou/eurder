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

        if (isNotProvided(createCustomerDto.getEmail())) {
            throw new NoEmailException();
        }

        if (isNotProvided(createCustomerDto.getFirstname())) {
            throw new NoFirstnameException();
        }

        Customer newCustomer = customerMapping.toCustomer(createCustomerDto);
        Customer savedCustomer = customerRepository.saveCustomer(newCustomer);
        return customerMapping.toCustomerDto(savedCustomer);
    }

    private boolean isNotProvided(String userInput) {
        return userInput == null || userInput.isEmpty() || userInput.isBlank();
    }
}
