package com.switchfully.eurder.users.admins;

import com.switchfully.eurder.users.admins.AdminRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AdminRepositoryTest {

    @Test
    void givenAdminRepository_thenADefaultAdminExists() {
        //GIVEN
        AdminRepository adminRepository = new AdminRepository();

        //THEN
        Assertions.assertThat(adminRepository.getAdmin("default").getUsername()).isEqualTo("default");
        Assertions.assertThat(adminRepository.getAdmin("default").getPassword()).isEqualTo("admin");
    }
}