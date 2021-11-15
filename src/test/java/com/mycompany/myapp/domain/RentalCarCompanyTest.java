package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RentalCarCompanyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RentalCarCompany.class);
        RentalCarCompany rentalCarCompany1 = new RentalCarCompany();
        rentalCarCompany1.setId(1L);
        RentalCarCompany rentalCarCompany2 = new RentalCarCompany();
        rentalCarCompany2.setId(rentalCarCompany1.getId());
        assertThat(rentalCarCompany1).isEqualTo(rentalCarCompany2);
        rentalCarCompany2.setId(2L);
        assertThat(rentalCarCompany1).isNotEqualTo(rentalCarCompany2);
        rentalCarCompany1.setId(null);
        assertThat(rentalCarCompany1).isNotEqualTo(rentalCarCompany2);
    }
}
