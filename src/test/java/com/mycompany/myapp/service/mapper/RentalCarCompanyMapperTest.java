package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RentalCarCompanyMapperTest {

    private RentalCarCompanyMapper rentalCarCompanyMapper;

    @BeforeEach
    public void setUp() {
        rentalCarCompanyMapper = new RentalCarCompanyMapperImpl();
    }
}
