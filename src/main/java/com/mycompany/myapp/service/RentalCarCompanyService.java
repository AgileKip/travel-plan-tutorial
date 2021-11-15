package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.RentalCarCompany;
import com.mycompany.myapp.repository.RentalCarCompanyRepository;
import com.mycompany.myapp.service.dto.RentalCarCompanyDTO;
import com.mycompany.myapp.service.mapper.RentalCarCompanyMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RentalCarCompany}.
 */
@Service
@Transactional
public class RentalCarCompanyService {

    private final Logger log = LoggerFactory.getLogger(RentalCarCompanyService.class);

    private final RentalCarCompanyRepository rentalCarCompanyRepository;

    private final RentalCarCompanyMapper rentalCarCompanyMapper;

    public RentalCarCompanyService(RentalCarCompanyRepository rentalCarCompanyRepository, RentalCarCompanyMapper rentalCarCompanyMapper) {
        this.rentalCarCompanyRepository = rentalCarCompanyRepository;
        this.rentalCarCompanyMapper = rentalCarCompanyMapper;
    }

    /**
     * Save a rentalCarCompany.
     *
     * @param rentalCarCompanyDTO the entity to save.
     * @return the persisted entity.
     */
    public RentalCarCompanyDTO save(RentalCarCompanyDTO rentalCarCompanyDTO) {
        log.debug("Request to save RentalCarCompany : {}", rentalCarCompanyDTO);
        RentalCarCompany rentalCarCompany = rentalCarCompanyMapper.toEntity(rentalCarCompanyDTO);
        rentalCarCompany = rentalCarCompanyRepository.save(rentalCarCompany);
        return rentalCarCompanyMapper.toDto(rentalCarCompany);
    }

    /**
     * Partially update a rentalCarCompany.
     *
     * @param rentalCarCompanyDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RentalCarCompanyDTO> partialUpdate(RentalCarCompanyDTO rentalCarCompanyDTO) {
        log.debug("Request to partially update RentalCarCompany : {}", rentalCarCompanyDTO);

        return rentalCarCompanyRepository
            .findById(rentalCarCompanyDTO.getId())
            .map(
                existingRentalCarCompany -> {
                    rentalCarCompanyMapper.partialUpdate(existingRentalCarCompany, rentalCarCompanyDTO);
                    return existingRentalCarCompany;
                }
            )
            .map(rentalCarCompanyRepository::save)
            .map(rentalCarCompanyMapper::toDto);
    }

    /**
     * Get all the rentalCarCompanies.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RentalCarCompanyDTO> findAll() {
        log.debug("Request to get all RentalCarCompanies");
        return rentalCarCompanyRepository
            .findAll()
            .stream()
            .map(rentalCarCompanyMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one rentalCarCompany by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RentalCarCompanyDTO> findOne(Long id) {
        log.debug("Request to get RentalCarCompany : {}", id);
        return rentalCarCompanyRepository.findById(id).map(rentalCarCompanyMapper::toDto);
    }

    /**
     * Delete the rentalCarCompany by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RentalCarCompany : {}", id);
        rentalCarCompanyRepository.deleteById(id);
    }
}
