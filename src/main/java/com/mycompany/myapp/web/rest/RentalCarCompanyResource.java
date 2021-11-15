package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.RentalCarCompanyRepository;
import com.mycompany.myapp.service.RentalCarCompanyService;
import com.mycompany.myapp.service.dto.RentalCarCompanyDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.RentalCarCompany}.
 */
@RestController
@RequestMapping("/api")
public class RentalCarCompanyResource {

    private final Logger log = LoggerFactory.getLogger(RentalCarCompanyResource.class);

    private static final String ENTITY_NAME = "rentalCarCompany";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RentalCarCompanyService rentalCarCompanyService;

    private final RentalCarCompanyRepository rentalCarCompanyRepository;

    public RentalCarCompanyResource(
        RentalCarCompanyService rentalCarCompanyService,
        RentalCarCompanyRepository rentalCarCompanyRepository
    ) {
        this.rentalCarCompanyService = rentalCarCompanyService;
        this.rentalCarCompanyRepository = rentalCarCompanyRepository;
    }

    /**
     * {@code POST  /rental-car-companies} : Create a new rentalCarCompany.
     *
     * @param rentalCarCompanyDTO the rentalCarCompanyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rentalCarCompanyDTO, or with status {@code 400 (Bad Request)} if the rentalCarCompany has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rental-car-companies")
    public ResponseEntity<RentalCarCompanyDTO> createRentalCarCompany(@RequestBody RentalCarCompanyDTO rentalCarCompanyDTO)
        throws URISyntaxException {
        log.debug("REST request to save RentalCarCompany : {}", rentalCarCompanyDTO);
        if (rentalCarCompanyDTO.getId() != null) {
            throw new BadRequestAlertException("A new rentalCarCompany cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RentalCarCompanyDTO result = rentalCarCompanyService.save(rentalCarCompanyDTO);
        return ResponseEntity
            .created(new URI("/api/rental-car-companies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rental-car-companies/:id} : Updates an existing rentalCarCompany.
     *
     * @param id the id of the rentalCarCompanyDTO to save.
     * @param rentalCarCompanyDTO the rentalCarCompanyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rentalCarCompanyDTO,
     * or with status {@code 400 (Bad Request)} if the rentalCarCompanyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rentalCarCompanyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rental-car-companies/{id}")
    public ResponseEntity<RentalCarCompanyDTO> updateRentalCarCompany(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RentalCarCompanyDTO rentalCarCompanyDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RentalCarCompany : {}, {}", id, rentalCarCompanyDTO);
        if (rentalCarCompanyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rentalCarCompanyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rentalCarCompanyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RentalCarCompanyDTO result = rentalCarCompanyService.save(rentalCarCompanyDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rentalCarCompanyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /rental-car-companies/:id} : Partial updates given fields of an existing rentalCarCompany, field will ignore if it is null
     *
     * @param id the id of the rentalCarCompanyDTO to save.
     * @param rentalCarCompanyDTO the rentalCarCompanyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rentalCarCompanyDTO,
     * or with status {@code 400 (Bad Request)} if the rentalCarCompanyDTO is not valid,
     * or with status {@code 404 (Not Found)} if the rentalCarCompanyDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the rentalCarCompanyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/rental-car-companies/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<RentalCarCompanyDTO> partialUpdateRentalCarCompany(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RentalCarCompanyDTO rentalCarCompanyDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RentalCarCompany partially : {}, {}", id, rentalCarCompanyDTO);
        if (rentalCarCompanyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rentalCarCompanyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rentalCarCompanyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RentalCarCompanyDTO> result = rentalCarCompanyService.partialUpdate(rentalCarCompanyDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rentalCarCompanyDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /rental-car-companies} : get all the rentalCarCompanies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rentalCarCompanies in body.
     */
    @GetMapping("/rental-car-companies")
    public List<RentalCarCompanyDTO> getAllRentalCarCompanies() {
        log.debug("REST request to get all RentalCarCompanies");
        return rentalCarCompanyService.findAll();
    }

    /**
     * {@code GET  /rental-car-companies/:id} : get the "id" rentalCarCompany.
     *
     * @param id the id of the rentalCarCompanyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rentalCarCompanyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rental-car-companies/{id}")
    public ResponseEntity<RentalCarCompanyDTO> getRentalCarCompany(@PathVariable Long id) {
        log.debug("REST request to get RentalCarCompany : {}", id);
        Optional<RentalCarCompanyDTO> rentalCarCompanyDTO = rentalCarCompanyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rentalCarCompanyDTO);
    }

    /**
     * {@code DELETE  /rental-car-companies/:id} : delete the "id" rentalCarCompany.
     *
     * @param id the id of the rentalCarCompanyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rental-car-companies/{id}")
    public ResponseEntity<Void> deleteRentalCarCompany(@PathVariable Long id) {
        log.debug("REST request to delete RentalCarCompany : {}", id);
        rentalCarCompanyService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
