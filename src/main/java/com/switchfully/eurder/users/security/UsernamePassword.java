package com.switchfully.eurder.users.security;

public class UsernamePassword {
    private final String password;
    private final String username;

    public UsernamePassword(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }
}
