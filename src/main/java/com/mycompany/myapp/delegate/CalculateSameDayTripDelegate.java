package com.mycompany.myapp.delegate;

import com.mycompany.myapp.service.dto.TravelPlanProcessDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class CalculateSameDayTripDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        TravelPlanProcessDTO travelPlanProcess = (TravelPlanProcessDTO) delegateExecution.getVariable("processInstance");
        Boolean moreThan1DayTrip = travelPlanProcess.getTravelPlan().getEndDate().isAfter(travelPlanProcess.getTravelPlan().getStartDate());
        delegateExecution.setVariable("moreThan1DayTrip", moreThan1DayTrip);
    }
}
