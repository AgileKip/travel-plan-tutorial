package com.mycompany.myapp.process.travelPlanProcess;

import com.mycompany.myapp.domain.TravelPlan;
import com.mycompany.myapp.domain.TravelPlanProcess;
import com.mycompany.myapp.service.dto.TravelPlanDTO;
import com.mycompany.myapp.service.dto.TravelPlanProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface TaskCarMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    TravelPlanProcessDTO toTravelPlanProcessDTO(TravelPlanProcess travelPlanProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "travelName", source = "travelName")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "carCompanyName", source = "carCompanyName")
    @Mapping(target = "carBookingNumber", source = "carBookingNumber")
    TravelPlanDTO toTravelPlanDTO(TravelPlan travelPlan);
}
