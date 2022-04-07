package com.switchfully.eurder.users.security;

import com.switchfully.eurder.users.admins.Admin;
import com.switchfully.eurder.users.admins.AdminRepository;
import com.switchfully.eurder.users.security.exceptions.UnauthorizedUserException;
import com.switchfully.eurder.users.security.exceptions.UnknownUserException;
import com.switchfully.eurder.users.security.exceptions.WrongPasswordException;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SecurityService {

    private final AdminRepository adminRepository;

    public SecurityService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void validateAuthorization(String authorization, Feature feature) {
        UsernamePassword usernamePassword = getUsernamePassword(authorization);

        User user = getUser(usernamePassword);

        if (user == null) {
            throw new UnknownUserException();
        }

        if (!user.hasCorrectPassword(usernamePassword.getPassword())){
            throw new WrongPasswordException();
        }

        if (!user.hasAccessTo(feature)) {
            throw new UnauthorizedUserException();
        }
    }

    private User getUser(UsernamePassword usernamePassword) {
        Admin admin = adminRepository.getAdmin(usernamePassword.getUsername());

        if (admin == null) {
            return null;
        }

        return new User(admin.getUsername(),admin.getPassword(),Role.ADMIN);
    }

    private UsernamePassword getUsernamePassword(String authorization) {
        String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String username = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":"));
        String password = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":") + 1);
        return new UsernamePassword(username, password);
    }
}
