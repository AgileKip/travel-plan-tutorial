package com.mycompany.myapp.service;

import org.springframework.stereotype.Component;

@Component
public class HotelService {
    public void confirmReservation(String hotelBookingNumber) {
        System.out.println("HotelService: ###########################################");
        System.out.println("HotelService: ###########################################");
        System.out.println("HotelService: ###########################################");
        System.out.println("HotelService:        CONFIRMING BOOKING HOTEL " + hotelBookingNumber);
        System.out.println("HotelService:        BOOKING HOTEL " + hotelBookingNumber + " CONFIRMED");
        System.out.println("HotelService: ###########################################");
        System.out.println("HotelService: ###########################################");
        System.out.println("HotelService: ###########################################\n\n\n");
    }
}
