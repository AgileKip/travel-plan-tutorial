package com.mycompany.myapp.delegate;

import com.mycompany.myapp.service.MailService;
import com.mycompany.myapp.service.dto.TravelPlanDTO;
import com.mycompany.myapp.service.dto.TravelPlanProcessDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Locale;

@Component
public class EmailTravelPlanSummaryDelegate implements JavaDelegate {

    @Autowired
    MailService mailService;

    @Autowired
    SpringTemplateEngine templateEngine;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        TravelPlanProcessDTO travelPlanProcess = (TravelPlanProcessDTO) delegateExecution.getVariable("processInstance");
        TravelPlanDTO travelPlan = travelPlanProcess.getTravelPlan();
        String to = travelPlan.getUserEmail();
        String subject = "[AgileKip] Summary of your travel " + travelPlan.getTravelName();
        Context context = new Context(Locale.getDefault());
        context.setVariable("travelPlan", travelPlan);
        String content = templateEngine.process("travelPlanProcess/travelPlanSummaryEmail", context);
        mailService.sendEmail(to, subject, content, false, true);
    }
}
