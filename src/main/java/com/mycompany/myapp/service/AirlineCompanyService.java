package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.AirlineCompany;
import com.mycompany.myapp.repository.AirlineCompanyRepository;
import com.mycompany.myapp.service.dto.AirlineCompanyDTO;
import com.mycompany.myapp.service.mapper.AirlineCompanyMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AirlineCompany}.
 */
@Service
@Transactional
public class AirlineCompanyService {

    private final Logger log = LoggerFactory.getLogger(AirlineCompanyService.class);

    private final AirlineCompanyRepository airlineCompanyRepository;

    private final AirlineCompanyMapper airlineCompanyMapper;

    public AirlineCompanyService(AirlineCompanyRepository airlineCompanyRepository, AirlineCompanyMapper airlineCompanyMapper) {
        this.airlineCompanyRepository = airlineCompanyRepository;
        this.airlineCompanyMapper = airlineCompanyMapper;
    }

    /**
     * Save a airlineCompany.
     *
     * @param airlineCompanyDTO the entity to save.
     * @return the persisted entity.
     */
    public AirlineCompanyDTO save(AirlineCompanyDTO airlineCompanyDTO) {
        log.debug("Request to save AirlineCompany : {}", airlineCompanyDTO);
        AirlineCompany airlineCompany = airlineCompanyMapper.toEntity(airlineCompanyDTO);
        airlineCompany = airlineCompanyRepository.save(airlineCompany);
        return airlineCompanyMapper.toDto(airlineCompany);
    }

    /**
     * Partially update a airlineCompany.
     *
     * @param airlineCompanyDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AirlineCompanyDTO> partialUpdate(AirlineCompanyDTO airlineCompanyDTO) {
        log.debug("Request to partially update AirlineCompany : {}", airlineCompanyDTO);

        return airlineCompanyRepository
            .findById(airlineCompanyDTO.getId())
            .map(
                existingAirlineCompany -> {
                    airlineCompanyMapper.partialUpdate(existingAirlineCompany, airlineCompanyDTO);
                    return existingAirlineCompany;
                }
            )
            .map(airlineCompanyRepository::save)
            .map(airlineCompanyMapper::toDto);
    }

    /**
     * Get all the airlineCompanies.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AirlineCompanyDTO> findAll() {
        log.debug("Request to get all AirlineCompanies");
        return airlineCompanyRepository
            .findAll()
            .stream()
            .map(airlineCompanyMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one airlineCompany by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AirlineCompanyDTO> findOne(Long id) {
        log.debug("Request to get AirlineCompany : {}", id);
        return airlineCompanyRepository.findById(id).map(airlineCompanyMapper::toDto);
    }

    /**
     * Delete the airlineCompany by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AirlineCompany : {}", id);
        airlineCompanyRepository.deleteById(id);
    }
}
