package com.switchfully.eurder.users.customers;

import com.switchfully.eurder.users.customers.dtos.CreateCustomerDto;
import com.switchfully.eurder.users.customers.dtos.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapping {

    public Customer toCustomer (CreateCustomerDto createCustomerDto) {
        return new Customer(
                createCustomerDto.getFirstname(),
                createCustomerDto.getLastname(),
                createCustomerDto.getEmail(),
                createCustomerDto.getAddress(),
                createCustomerDto.getPhoneNumber()
        );
    }

    public CustomerDto toCustomerDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPhoneNumber()
        );
    }
}
