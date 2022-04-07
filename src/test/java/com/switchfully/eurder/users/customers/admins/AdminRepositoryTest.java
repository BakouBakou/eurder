package com.switchfully.eurder.users.customers.admins;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AdminRepositoryTest {

    @Test
    void givenAdminRepository_thenADefaultAdminExists() {
        //GIVEN
        AdminRepository adminRepository = new AdminRepository();

        //THEN
        Assertions.assertThat(adminRepository.getAdmin().size()).isGreaterThan(0);
        Assertions.assertThat(adminRepository.getAdmin().get("default").getUsername()).isEqualTo("default");
        Assertions.assertThat(adminRepository.getAdmin().get("default").getPassword()).isEqualTo("admin");
    }
}