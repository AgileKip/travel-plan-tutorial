package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RentalCarCompanyDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RentalCarCompanyDTO.class);
        RentalCarCompanyDTO rentalCarCompanyDTO1 = new RentalCarCompanyDTO();
        rentalCarCompanyDTO1.setId(1L);
        RentalCarCompanyDTO rentalCarCompanyDTO2 = new RentalCarCompanyDTO();
        assertThat(rentalCarCompanyDTO1).isNotEqualTo(rentalCarCompanyDTO2);
        rentalCarCompanyDTO2.setId(rentalCarCompanyDTO1.getId());
        assertThat(rentalCarCompanyDTO1).isEqualTo(rentalCarCompanyDTO2);
        rentalCarCompanyDTO2.setId(2L);
        assertThat(rentalCarCompanyDTO1).isNotEqualTo(rentalCarCompanyDTO2);
        rentalCarCompanyDTO1.setId(null);
        assertThat(rentalCarCompanyDTO1).isNotEqualTo(rentalCarCompanyDTO2);
    }
}
