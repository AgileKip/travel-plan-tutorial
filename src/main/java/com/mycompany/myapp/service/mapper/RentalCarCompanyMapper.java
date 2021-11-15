package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.RentalCarCompanyDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RentalCarCompany} and its DTO {@link RentalCarCompanyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RentalCarCompanyMapper extends EntityMapper<RentalCarCompanyDTO, RentalCarCompany> {
    @Named("name")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    RentalCarCompanyDTO toDtoName(RentalCarCompany rentalCarCompany);
}
