package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.AirlineCompanyRepository;
import com.mycompany.myapp.service.AirlineCompanyService;
import com.mycompany.myapp.service.dto.AirlineCompanyDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.AirlineCompany}.
 */
@RestController
@RequestMapping("/api")
public class AirlineCompanyResource {

    private final Logger log = LoggerFactory.getLogger(AirlineCompanyResource.class);

    private static final String ENTITY_NAME = "airlineCompany";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AirlineCompanyService airlineCompanyService;

    private final AirlineCompanyRepository airlineCompanyRepository;

    public AirlineCompanyResource(AirlineCompanyService airlineCompanyService, AirlineCompanyRepository airlineCompanyRepository) {
        this.airlineCompanyService = airlineCompanyService;
        this.airlineCompanyRepository = airlineCompanyRepository;
    }

    /**
     * {@code POST  /airline-companies} : Create a new airlineCompany.
     *
     * @param airlineCompanyDTO the airlineCompanyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new airlineCompanyDTO, or with status {@code 400 (Bad Request)} if the airlineCompany has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/airline-companies")
    public ResponseEntity<AirlineCompanyDTO> createAirlineCompany(@RequestBody AirlineCompanyDTO airlineCompanyDTO)
        throws URISyntaxException {
        log.debug("REST request to save AirlineCompany : {}", airlineCompanyDTO);
        if (airlineCompanyDTO.getId() != null) {
            throw new BadRequestAlertException("A new airlineCompany cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AirlineCompanyDTO result = airlineCompanyService.save(airlineCompanyDTO);
        return ResponseEntity
            .created(new URI("/api/airline-companies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /airline-companies/:id} : Updates an existing airlineCompany.
     *
     * @param id the id of the airlineCompanyDTO to save.
     * @param airlineCompanyDTO the airlineCompanyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated airlineCompanyDTO,
     * or with status {@code 400 (Bad Request)} if the airlineCompanyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the airlineCompanyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/airline-companies/{id}")
    public ResponseEntity<AirlineCompanyDTO> updateAirlineCompany(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AirlineCompanyDTO airlineCompanyDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AirlineCompany : {}, {}", id, airlineCompanyDTO);
        if (airlineCompanyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, airlineCompanyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!airlineCompanyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AirlineCompanyDTO result = airlineCompanyService.save(airlineCompanyDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, airlineCompanyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /airline-companies/:id} : Partial updates given fields of an existing airlineCompany, field will ignore if it is null
     *
     * @param id the id of the airlineCompanyDTO to save.
     * @param airlineCompanyDTO the airlineCompanyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated airlineCompanyDTO,
     * or with status {@code 400 (Bad Request)} if the airlineCompanyDTO is not valid,
     * or with status {@code 404 (Not Found)} if the airlineCompanyDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the airlineCompanyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/airline-companies/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AirlineCompanyDTO> partialUpdateAirlineCompany(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AirlineCompanyDTO airlineCompanyDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AirlineCompany partially : {}, {}", id, airlineCompanyDTO);
        if (airlineCompanyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, airlineCompanyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!airlineCompanyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AirlineCompanyDTO> result = airlineCompanyService.partialUpdate(airlineCompanyDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, airlineCompanyDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /airline-companies} : get all the airlineCompanies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of airlineCompanies in body.
     */
    @GetMapping("/airline-companies")
    public List<AirlineCompanyDTO> getAllAirlineCompanies() {
        log.debug("REST request to get all AirlineCompanies");
        return airlineCompanyService.findAll();
    }

    /**
     * {@code GET  /airline-companies/:id} : get the "id" airlineCompany.
     *
     * @param id the id of the airlineCompanyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the airlineCompanyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/airline-companies/{id}")
    public ResponseEntity<AirlineCompanyDTO> getAirlineCompany(@PathVariable Long id) {
        log.debug("REST request to get AirlineCompany : {}", id);
        Optional<AirlineCompanyDTO> airlineCompanyDTO = airlineCompanyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(airlineCompanyDTO);
    }

    /**
     * {@code DELETE  /airline-companies/:id} : delete the "id" airlineCompany.
     *
     * @param id the id of the airlineCompanyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/airline-companies/{id}")
    public ResponseEntity<Void> deleteAirlineCompany(@PathVariable Long id) {
        log.debug("REST request to delete AirlineCompany : {}", id);
        airlineCompanyService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
