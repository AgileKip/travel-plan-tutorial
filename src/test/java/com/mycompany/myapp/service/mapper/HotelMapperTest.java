package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HotelMapperTest {

    private HotelMapper hotelMapper;

    @BeforeEach
    public void setUp() {
        hotelMapper = new HotelMapperImpl();
    }
}
