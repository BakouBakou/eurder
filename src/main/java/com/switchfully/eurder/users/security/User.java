package com.switchfully.eurder.users.security;

public class User {
    private final String username;
    private final String password;
    private final Role role;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean hasAccessTo(Feature feature) {
        return role.containsFeature(feature);
    }

    public boolean hasCorrectPassword(String passwordEntered) {
        return this.password.equals(passwordEntered);
    }
}
