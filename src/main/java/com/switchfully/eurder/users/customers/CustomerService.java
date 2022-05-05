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
import java.util.Objects;

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

        return customerMapping.toCustomerDto(findCustomerById(id));
    }


    public CustomerDto createCustomer(CreateCustomerDto createCustomerDto) {
        logger.info("createCustomer started");

        checkCreateCustomerDtoFields(createCustomerDto);

        Customer newCustomer = customerMapping.toCustomer(createCustomerDto);
        Customer savedCustomer = customerRepository.save(newCustomer);
        logger.info("customer created in the database with id: " + savedCustomer.getId());
        return customerMapping.toCustomerDto(savedCustomer);
    }

    public Customer findCustomerById(String id) {

        return customerRepository.findById(id)
                .map(customer -> {
                    logger.info("Customer has been found");
                    return customer;
                })
                .orElseThrow(() -> {
                    logger.error(new CustomerNotFoundException(id).getMessage());
                    return new CustomerNotFoundException(id);
                });
    }

    private void checkCreateCustomerDtoFields(CreateCustomerDto createCustomerDto) {
        //        @NotNull //javax, instance field
        //  could put on the DTO (sooner is better), check in the constructor, or even entity itself to reduce duplication
        // validator class grouping all the checks
        //Objects.requireNonNull(), and other existing methods for such checks might exist
        checkInput(isNotProvided(createCustomerDto.getEmail()), new NoEmailException());
        checkInput(isNotProvided(createCustomerDto.getFirstname()), new NoFirstnameException());
        checkInput(isNotProvided(createCustomerDto.getLastname()), new NoLastnameException());
        checkInput(isNotProvided(createCustomerDto.getAddress()), new NoAddressException());

        checkInput(isValidEmail(createCustomerDto), new InvalidEmailFormatException());

        // this kind of checks can only happen in the service because we need the repository to find the email
        checkInput(isExistingEmail(createCustomerDto), new EmailAlreadyInUseException());
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
