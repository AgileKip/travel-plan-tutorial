package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.AirlineCompany;
import com.mycompany.myapp.repository.AirlineCompanyRepository;
import com.mycompany.myapp.service.dto.AirlineCompanyDTO;
import com.mycompany.myapp.service.mapper.AirlineCompanyMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AirlineCompanyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AirlineCompanyResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/airline-companies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AirlineCompanyRepository airlineCompanyRepository;

    @Autowired
    private AirlineCompanyMapper airlineCompanyMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAirlineCompanyMockMvc;

    private AirlineCompany airlineCompany;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AirlineCompany createEntity(EntityManager em) {
        AirlineCompany airlineCompany = new AirlineCompany().name(DEFAULT_NAME).website(DEFAULT_WEBSITE).email(DEFAULT_EMAIL);
        return airlineCompany;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AirlineCompany createUpdatedEntity(EntityManager em) {
        AirlineCompany airlineCompany = new AirlineCompany().name(UPDATED_NAME).website(UPDATED_WEBSITE).email(UPDATED_EMAIL);
        return airlineCompany;
    }

    @BeforeEach
    public void initTest() {
        airlineCompany = createEntity(em);
    }

    @Test
    @Transactional
    void createAirlineCompany() throws Exception {
        int databaseSizeBeforeCreate = airlineCompanyRepository.findAll().size();
        // Create the AirlineCompany
        AirlineCompanyDTO airlineCompanyDTO = airlineCompanyMapper.toDto(airlineCompany);
        restAirlineCompanyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(airlineCompanyDTO))
            )
            .andExpect(status().isCreated());

        // Validate the AirlineCompany in the database
        List<AirlineCompany> airlineCompanyList = airlineCompanyRepository.findAll();
        assertThat(airlineCompanyList).hasSize(databaseSizeBeforeCreate + 1);
        AirlineCompany testAirlineCompany = airlineCompanyList.get(airlineCompanyList.size() - 1);
        assertThat(testAirlineCompany.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAirlineCompany.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testAirlineCompany.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    void createAirlineCompanyWithExistingId() throws Exception {
        // Create the AirlineCompany with an existing ID
        airlineCompany.setId(1L);
        AirlineCompanyDTO airlineCompanyDTO = airlineCompanyMapper.toDto(airlineCompany);

        int databaseSizeBeforeCreate = airlineCompanyRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAirlineCompanyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(airlineCompanyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AirlineCompany in the database
        List<AirlineCompany> airlineCompanyList = airlineCompanyRepository.findAll();
        assertThat(airlineCompanyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAirlineCompanies() throws Exception {
        // Initialize the database
        airlineCompanyRepository.saveAndFlush(airlineCompany);

        // Get all the airlineCompanyList
        restAirlineCompanyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(airlineCompany.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }

    @Test
    @Transactional
    void getAirlineCompany() throws Exception {
        // Initialize the database
        airlineCompanyRepository.saveAndFlush(airlineCompany);

        // Get the airlineCompany
        restAirlineCompanyMockMvc
            .perform(get(ENTITY_API_URL_ID, airlineCompany.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(airlineCompany.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }

    @Test
    @Transactional
    void getNonExistingAirlineCompany() throws Exception {
        // Get the airlineCompany
        restAirlineCompanyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAirlineCompany() throws Exception {
        // Initialize the database
        airlineCompanyRepository.saveAndFlush(airlineCompany);

        int databaseSizeBeforeUpdate = airlineCompanyRepository.findAll().size();

        // Update the airlineCompany
        AirlineCompany updatedAirlineCompany = airlineCompanyRepository.findById(airlineCompany.getId()).get();
        // Disconnect from session so that the updates on updatedAirlineCompany are not directly saved in db
        em.detach(updatedAirlineCompany);
        updatedAirlineCompany.name(UPDATED_NAME).website(UPDATED_WEBSITE).email(UPDATED_EMAIL);
        AirlineCompanyDTO airlineCompanyDTO = airlineCompanyMapper.toDto(updatedAirlineCompany);

        restAirlineCompanyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, airlineCompanyDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(airlineCompanyDTO))
            )
            .andExpect(status().isOk());

        // Validate the AirlineCompany in the database
        List<AirlineCompany> airlineCompanyList = airlineCompanyRepository.findAll();
        assertThat(airlineCompanyList).hasSize(databaseSizeBeforeUpdate);
        AirlineCompany testAirlineCompany = airlineCompanyList.get(airlineCompanyList.size() - 1);
        assertThat(testAirlineCompany.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAirlineCompany.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testAirlineCompany.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void putNonExistingAirlineCompany() throws Exception {
        int databaseSizeBeforeUpdate = airlineCompanyRepository.findAll().size();
        airlineCompany.setId(count.incrementAndGet());

        // Create the AirlineCompany
        AirlineCompanyDTO airlineCompanyDTO = airlineCompanyMapper.toDto(airlineCompany);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAirlineCompanyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, airlineCompanyDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(airlineCompanyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AirlineCompany in the database
        List<AirlineCompany> airlineCompanyList = airlineCompanyRepository.findAll();
        assertThat(airlineCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAirlineCompany() throws Exception {
        int databaseSizeBeforeUpdate = airlineCompanyRepository.findAll().size();
        airlineCompany.setId(count.incrementAndGet());

        // Create the AirlineCompany
        AirlineCompanyDTO airlineCompanyDTO = airlineCompanyMapper.toDto(airlineCompany);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAirlineCompanyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(airlineCompanyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AirlineCompany in the database
        List<AirlineCompany> airlineCompanyList = airlineCompanyRepository.findAll();
        assertThat(airlineCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAirlineCompany() throws Exception {
        int databaseSizeBeforeUpdate = airlineCompanyRepository.findAll().size();
        airlineCompany.setId(count.incrementAndGet());

        // Create the AirlineCompany
        AirlineCompanyDTO airlineCompanyDTO = airlineCompanyMapper.toDto(airlineCompany);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAirlineCompanyMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(airlineCompanyDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AirlineCompany in the database
        List<AirlineCompany> airlineCompanyList = airlineCompanyRepository.findAll();
        assertThat(airlineCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAirlineCompanyWithPatch() throws Exception {
        // Initialize the database
        airlineCompanyRepository.saveAndFlush(airlineCompany);

        int databaseSizeBeforeUpdate = airlineCompanyRepository.findAll().size();

        // Update the airlineCompany using partial update
        AirlineCompany partialUpdatedAirlineCompany = new AirlineCompany();
        partialUpdatedAirlineCompany.setId(airlineCompany.getId());

        partialUpdatedAirlineCompany.name(UPDATED_NAME).website(UPDATED_WEBSITE);

        restAirlineCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAirlineCompany.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAirlineCompany))
            )
            .andExpect(status().isOk());

        // Validate the AirlineCompany in the database
        List<AirlineCompany> airlineCompanyList = airlineCompanyRepository.findAll();
        assertThat(airlineCompanyList).hasSize(databaseSizeBeforeUpdate);
        AirlineCompany testAirlineCompany = airlineCompanyList.get(airlineCompanyList.size() - 1);
        assertThat(testAirlineCompany.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAirlineCompany.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testAirlineCompany.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    void fullUpdateAirlineCompanyWithPatch() throws Exception {
        // Initialize the database
        airlineCompanyRepository.saveAndFlush(airlineCompany);

        int databaseSizeBeforeUpdate = airlineCompanyRepository.findAll().size();

        // Update the airlineCompany using partial update
        AirlineCompany partialUpdatedAirlineCompany = new AirlineCompany();
        partialUpdatedAirlineCompany.setId(airlineCompany.getId());

        partialUpdatedAirlineCompany.name(UPDATED_NAME).website(UPDATED_WEBSITE).email(UPDATED_EMAIL);

        restAirlineCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAirlineCompany.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAirlineCompany))
            )
            .andExpect(status().isOk());

        // Validate the AirlineCompany in the database
        List<AirlineCompany> airlineCompanyList = airlineCompanyRepository.findAll();
        assertThat(airlineCompanyList).hasSize(databaseSizeBeforeUpdate);
        AirlineCompany testAirlineCompany = airlineCompanyList.get(airlineCompanyList.size() - 1);
        assertThat(testAirlineCompany.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAirlineCompany.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testAirlineCompany.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void patchNonExistingAirlineCompany() throws Exception {
        int databaseSizeBeforeUpdate = airlineCompanyRepository.findAll().size();
        airlineCompany.setId(count.incrementAndGet());

        // Create the AirlineCompany
        AirlineCompanyDTO airlineCompanyDTO = airlineCompanyMapper.toDto(airlineCompany);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAirlineCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, airlineCompanyDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(airlineCompanyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AirlineCompany in the database
        List<AirlineCompany> airlineCompanyList = airlineCompanyRepository.findAll();
        assertThat(airlineCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAirlineCompany() throws Exception {
        int databaseSizeBeforeUpdate = airlineCompanyRepository.findAll().size();
        airlineCompany.setId(count.incrementAndGet());

        // Create the AirlineCompany
        AirlineCompanyDTO airlineCompanyDTO = airlineCompanyMapper.toDto(airlineCompany);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAirlineCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(airlineCompanyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AirlineCompany in the database
        List<AirlineCompany> airlineCompanyList = airlineCompanyRepository.findAll();
        assertThat(airlineCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAirlineCompany() throws Exception {
        int databaseSizeBeforeUpdate = airlineCompanyRepository.findAll().size();
        airlineCompany.setId(count.incrementAndGet());

        // Create the AirlineCompany
        AirlineCompanyDTO airlineCompanyDTO = airlineCompanyMapper.toDto(airlineCompany);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAirlineCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(airlineCompanyDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AirlineCompany in the database
        List<AirlineCompany> airlineCompanyList = airlineCompanyRepository.findAll();
        assertThat(airlineCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAirlineCompany() throws Exception {
        // Initialize the database
        airlineCompanyRepository.saveAndFlush(airlineCompany);

        int databaseSizeBeforeDelete = airlineCompanyRepository.findAll().size();

        // Delete the airlineCompany
        restAirlineCompanyMockMvc
            .perform(delete(ENTITY_API_URL_ID, airlineCompany.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AirlineCompany> airlineCompanyList = airlineCompanyRepository.findAll();
        assertThat(airlineCompanyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
