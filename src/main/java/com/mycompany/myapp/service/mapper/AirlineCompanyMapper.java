package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.AirlineCompanyDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AirlineCompany} and its DTO {@link AirlineCompanyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AirlineCompanyMapper extends EntityMapper<AirlineCompanyDTO, AirlineCompany> {
    @Named("name")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    AirlineCompanyDTO toDtoName(AirlineCompany airlineCompany);
}
