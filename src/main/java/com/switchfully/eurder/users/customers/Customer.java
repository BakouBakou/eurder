package com.switchfully.eurder.users.customers;

import java.util.UUID;

public class Customer {

    private final String id;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String address;
    private final String phoneNumber;

    public Customer(String firstname, String lastname, String email, String address, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
