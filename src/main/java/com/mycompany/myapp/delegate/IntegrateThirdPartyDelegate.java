package com.mycompany.myapp.delegate;

import com.mycompany.myapp.service.AirlineService;
import com.mycompany.myapp.service.HotelService;
import com.mycompany.myapp.service.RentCarService;
import com.mycompany.myapp.service.dto.TravelPlanProcessDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IntegrateThirdPartyDelegate implements JavaDelegate {

    @Autowired
    AirlineService airlineService;

    @Autowired
    HotelService hotelService;

    @Autowired
    RentCarService rentCarService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        TravelPlanProcessDTO travelPlanProcess = (TravelPlanProcessDTO) delegateExecution.getVariable("processInstance");

        //Confirming the flight
        airlineService.confirmFlight(travelPlanProcess.getTravelPlan().getAirlineTicketNumber());

        //Confirming the hotel booking
        //hotelService.confirmReservation(travelPlanProcess.getTravelPlan().getHotelBookingNumber());

        //Confirming the car booking
        rentCarService.confirmReservation(travelPlanProcess.getTravelPlan().getCarBookingNumber());
    }
}
