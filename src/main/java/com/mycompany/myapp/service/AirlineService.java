package com.mycompany.myapp.service;

import org.springframework.stereotype.Component;

@Component
public class AirlineService {

    public void confirmFlight(String airlineTicketNumber) {
        System.out.println("AirlineService: ###########################################");
        System.out.println("AirlineService: ###########################################");
        System.out.println("AirlineService: ###########################################");
        System.out.println("AirlineService:        CONFIRMING FLIGHT " + airlineTicketNumber);
        System.out.println("AirlineService:        FLIGHT " + airlineTicketNumber + " CONFIRMED");
        System.out.println("AirlineService: ###########################################");
        System.out.println("AirlineService: ###########################################");
        System.out.println("AirlineService: ###########################################\n\n\n");
    }
}
