package com.switchfully.eurder.users.customers;

import com.switchfully.eurder.users.customers.dtos.CreateCustomerDto;
import com.switchfully.eurder.users.customers.dtos.CustomerDto;
import com.switchfully.eurder.users.customers.exceptions.NoAddressException;
import com.switchfully.eurder.users.customers.exceptions.NoEmailException;
import com.switchfully.eurder.users.customers.exceptions.NoFirstnameException;
import com.switchfully.eurder.users.customers.exceptions.NoLastnameException;
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

        checkInput(createCustomerDto.getEmail(), new NoEmailException());
        checkInput(createCustomerDto.getFirstname(), new NoFirstnameException());
        checkInput(createCustomerDto.getLastname(), new NoLastnameException());
        checkInput(createCustomerDto.getAddress(), new NoAddressException());

        Customer newCustomer = customerMapping.toCustomer(createCustomerDto);
        Customer savedCustomer = customerRepository.saveCustomer(newCustomer);
        return customerMapping.toCustomerDto(savedCustomer);
    }

    private void checkInput(String inputToCheck, RuntimeException exceptionToThrow) {
        if (isNotProvided(inputToCheck)) {
            throw exceptionToThrow;
        }
    }

    private boolean isNotProvided(String userInput) {
        return userInput == null || userInput.isEmpty() || userInput.isBlank();
    }
}
