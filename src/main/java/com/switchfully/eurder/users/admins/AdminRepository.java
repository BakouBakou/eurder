package com.switchfully.eurder.users.admins;

import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AdminRepository {

    private final ConcurrentHashMap<String, Admin> adminDatabase = new ConcurrentHashMap<>();

    public AdminRepository() {
        Admin defaultAdmin = new Admin("default", "admin");
        this.adminDatabase.put(defaultAdmin.getUsername(), defaultAdmin);
    }

    public Admin getAdmin(String username) {
        return adminDatabase.get(username);
    }

}
