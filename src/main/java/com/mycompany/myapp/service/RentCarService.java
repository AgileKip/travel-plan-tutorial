package com.mycompany.myapp.service;

import org.springframework.stereotype.Component;

@Component
public class RentCarService {
    public void confirmReservation(String carBookingNumber) {
        System.out.println("RentCarService: ###########################################");
        System.out.println("RentCarService: ###########################################");
        System.out.println("RentCarService: ###########################################");
        System.out.println("RentCarService:        CONFIRMING BOOKING CAR " + carBookingNumber);
        System.out.println("RentCarService:        BOOKING CAR " + carBookingNumber + " CONFIRMED");
        System.out.println("RentCarService: ###########################################");
        System.out.println("RentCarService: ###########################################");
        System.out.println("RentCarService: ###########################################\n\n\n");
    }
}
