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


        checkInput(isNotProvided(createCustomerDto.getEmail()), new NoEmailException());
        checkInput(isNotProvided(createCustomerDto.getFirstname()), new NoFirstnameException());
        checkInput(isNotProvided(createCustomerDto.getLastname()), new NoLastnameException());
        checkInput(isNotProvided(createCustomerDto.getAddress()), new NoAddressException());

        checkInput(isValidEmail(createCustomerDto), new InvalidEmailFormatException());

        Customer newCustomer = customerMapping.toCustomer(createCustomerDto);
        Customer savedCustomer = customerRepository.saveCustomer(newCustomer);
        return customerMapping.toCustomerDto(savedCustomer);
    }


    private void checkInput(Boolean isInvalidInput, RuntimeException exceptionToThrow) {
        if (isInvalidInput) {
            throw exceptionToThrow;
        }
    }

    private boolean isValidEmail(CreateCustomerDto createCustomerDto) {
        return !createCustomerDto.getEmail().matches("^(\\S+)@(\\S+)\\.([a-zA-Z]+)$");
    }
    private boolean isNotProvided(String userInput) {
        return userInput == null || userInput.isEmpty() || userInput.isBlank();
    }
}
