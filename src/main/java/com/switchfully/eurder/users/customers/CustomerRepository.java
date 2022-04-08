package com.switchfully.eurder.users.customers;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CustomerRepository {

    public final ConcurrentHashMap<String,Customer> customersDatabase = new ConcurrentHashMap<>();

    public Customer saveCustomer(Customer customer) {
        customersDatabase.put(customer.getId(), customer);
        return customersDatabase.get(customer.getId());
    }

    public Optional<Customer> findCustomerByEmail(String email) {
        return customersDatabase.values().stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findAny();
    }

    public Optional<Customer> findCustomerById(String id) {
        return customersDatabase.values().stream()
                .filter(customer -> customer.getId().equals(id))
                .findAny();
    }

    public List<Customer> getAllCustomers() {
        return customersDatabase.values().stream().toList();
    }
}
