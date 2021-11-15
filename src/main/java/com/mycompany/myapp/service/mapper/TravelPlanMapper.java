package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.TravelPlanDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TravelPlan} and its DTO {@link TravelPlanDTO}.
 */
@Mapper(componentModel = "spring", uses = { AirlineCompanyMapper.class, HotelMapper.class, RentalCarCompanyMapper.class })
public interface TravelPlanMapper extends EntityMapper<TravelPlanDTO, TravelPlan> {
    @Mapping(target = "airlineCompany", source = "airlineCompany", qualifiedByName = "name")
    @Mapping(target = "hotel", source = "hotel", qualifiedByName = "name")
    @Mapping(target = "rentalCarCompany", source = "rentalCarCompany", qualifiedByName = "name")
    TravelPlanDTO toDto(TravelPlan s);
}
