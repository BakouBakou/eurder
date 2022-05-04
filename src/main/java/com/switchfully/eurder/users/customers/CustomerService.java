package com.switchfully.eurder.users.customers;

import com.switchfully.eurder.orders.exceptions.CustomerNotFoundException;
import com.switchfully.eurder.users.customers.dtos.CreateCustomerDto;
import com.switchfully.eurder.users.customers.dtos.CustomerDto;
import com.switchfully.eurder.users.customers.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerMapping customerMapping;
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public CustomerService(CustomerRepository customerRepository, CustomerMapping customerMapping) {
        this.customerRepository = customerRepository;
        this.customerMapping = customerMapping;
    }

    public List<CustomerDto> getAllCustomers() {
        logger.info("Getting all customers");
        return customerMapping.toCustomerDtoList(customerRepository.findAll());
    }

    public CustomerDto getCustomer(String id) {
        logger.info("Getting customer with id " + id);

        if (customerRepository.findById(id).isEmpty()) {
            logger.error(new CustomerNotFoundException(id).getMessage());
            throw new CustomerNotFoundException(id);
        }

        logger.info("Customer has been found");
        return customerMapping.toCustomerDto(customerRepository.findById(id).get());
    }

    public CustomerDto createCustomer(CreateCustomerDto createCustomerDto) {
        logger.info("createCustomer started");

//        @NotNull //javax, instance field
        checkInput(isNotProvided(createCustomerDto.getEmail()), new NoEmailException());
        checkInput(isNotProvided(createCustomerDto.getFirstname()), new NoFirstnameException());
        checkInput(isNotProvided(createCustomerDto.getLastname()), new NoLastnameException());
        checkInput(isNotProvided(createCustomerDto.getAddress()), new NoAddressException());

        checkInput(isValidEmail(createCustomerDto), new InvalidEmailFormatException());

        checkInput(isExistingEmail(createCustomerDto), new EmailAlreadyInUseException());

        Customer newCustomer = customerMapping.toCustomer(createCustomerDto);
        Customer savedCustomer = customerRepository.save(newCustomer);
        logger.info("customer created in the database with id: " + savedCustomer.getId());
        return customerMapping.toCustomerDto(savedCustomer);
    }

    private void checkInput(Boolean isInvalidInput, RuntimeException exceptionToThrow) {
        if (isInvalidInput) {
            logger.error(exceptionToThrow.getMessage());
            throw exceptionToThrow;
        }
    }


    private boolean isValidEmail(CreateCustomerDto createCustomerDto) {
        return !createCustomerDto.getEmail().matches("^(\\S+)@(\\S+)\\.([a-zA-Z]+)$");
    }
    private boolean isExistingEmail(CreateCustomerDto createCustomerDto) {
        return customerRepository.findByEmail(createCustomerDto.getEmail()).isPresent();
    }
    private boolean isNotProvided(String userInput) {
        return userInput == null || userInput.isEmpty() || userInput.isBlank();
    }

}
