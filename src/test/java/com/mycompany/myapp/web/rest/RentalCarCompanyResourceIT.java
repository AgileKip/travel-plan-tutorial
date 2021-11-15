package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.RentalCarCompany;
import com.mycompany.myapp.repository.RentalCarCompanyRepository;
import com.mycompany.myapp.service.dto.RentalCarCompanyDTO;
import com.mycompany.myapp.service.mapper.RentalCarCompanyMapper;
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
 * Integration tests for the {@link RentalCarCompanyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RentalCarCompanyResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/rental-car-companies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RentalCarCompanyRepository rentalCarCompanyRepository;

    @Autowired
    private RentalCarCompanyMapper rentalCarCompanyMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRentalCarCompanyMockMvc;

    private RentalCarCompany rentalCarCompany;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RentalCarCompany createEntity(EntityManager em) {
        RentalCarCompany rentalCarCompany = new RentalCarCompany().name(DEFAULT_NAME).website(DEFAULT_WEBSITE).email(DEFAULT_EMAIL);
        return rentalCarCompany;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RentalCarCompany createUpdatedEntity(EntityManager em) {
        RentalCarCompany rentalCarCompany = new RentalCarCompany().name(UPDATED_NAME).website(UPDATED_WEBSITE).email(UPDATED_EMAIL);
        return rentalCarCompany;
    }

    @BeforeEach
    public void initTest() {
        rentalCarCompany = createEntity(em);
    }

    @Test
    @Transactional
    void createRentalCarCompany() throws Exception {
        int databaseSizeBeforeCreate = rentalCarCompanyRepository.findAll().size();
        // Create the RentalCarCompany
        RentalCarCompanyDTO rentalCarCompanyDTO = rentalCarCompanyMapper.toDto(rentalCarCompany);
        restRentalCarCompanyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rentalCarCompanyDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RentalCarCompany in the database
        List<RentalCarCompany> rentalCarCompanyList = rentalCarCompanyRepository.findAll();
        assertThat(rentalCarCompanyList).hasSize(databaseSizeBeforeCreate + 1);
        RentalCarCompany testRentalCarCompany = rentalCarCompanyList.get(rentalCarCompanyList.size() - 1);
        assertThat(testRentalCarCompany.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRentalCarCompany.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testRentalCarCompany.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    void createRentalCarCompanyWithExistingId() throws Exception {
        // Create the RentalCarCompany with an existing ID
        rentalCarCompany.setId(1L);
        RentalCarCompanyDTO rentalCarCompanyDTO = rentalCarCompanyMapper.toDto(rentalCarCompany);

        int databaseSizeBeforeCreate = rentalCarCompanyRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRentalCarCompanyMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rentalCarCompanyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RentalCarCompany in the database
        List<RentalCarCompany> rentalCarCompanyList = rentalCarCompanyRepository.findAll();
        assertThat(rentalCarCompanyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRentalCarCompanies() throws Exception {
        // Initialize the database
        rentalCarCompanyRepository.saveAndFlush(rentalCarCompany);

        // Get all the rentalCarCompanyList
        restRentalCarCompanyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rentalCarCompany.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }

    @Test
    @Transactional
    void getRentalCarCompany() throws Exception {
        // Initialize the database
        rentalCarCompanyRepository.saveAndFlush(rentalCarCompany);

        // Get the rentalCarCompany
        restRentalCarCompanyMockMvc
            .perform(get(ENTITY_API_URL_ID, rentalCarCompany.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rentalCarCompany.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }

    @Test
    @Transactional
    void getNonExistingRentalCarCompany() throws Exception {
        // Get the rentalCarCompany
        restRentalCarCompanyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRentalCarCompany() throws Exception {
        // Initialize the database
        rentalCarCompanyRepository.saveAndFlush(rentalCarCompany);

        int databaseSizeBeforeUpdate = rentalCarCompanyRepository.findAll().size();

        // Update the rentalCarCompany
        RentalCarCompany updatedRentalCarCompany = rentalCarCompanyRepository.findById(rentalCarCompany.getId()).get();
        // Disconnect from session so that the updates on updatedRentalCarCompany are not directly saved in db
        em.detach(updatedRentalCarCompany);
        updatedRentalCarCompany.name(UPDATED_NAME).website(UPDATED_WEBSITE).email(UPDATED_EMAIL);
        RentalCarCompanyDTO rentalCarCompanyDTO = rentalCarCompanyMapper.toDto(updatedRentalCarCompany);

        restRentalCarCompanyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rentalCarCompanyDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rentalCarCompanyDTO))
            )
            .andExpect(status().isOk());

        // Validate the RentalCarCompany in the database
        List<RentalCarCompany> rentalCarCompanyList = rentalCarCompanyRepository.findAll();
        assertThat(rentalCarCompanyList).hasSize(databaseSizeBeforeUpdate);
        RentalCarCompany testRentalCarCompany = rentalCarCompanyList.get(rentalCarCompanyList.size() - 1);
        assertThat(testRentalCarCompany.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRentalCarCompany.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testRentalCarCompany.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void putNonExistingRentalCarCompany() throws Exception {
        int databaseSizeBeforeUpdate = rentalCarCompanyRepository.findAll().size();
        rentalCarCompany.setId(count.incrementAndGet());

        // Create the RentalCarCompany
        RentalCarCompanyDTO rentalCarCompanyDTO = rentalCarCompanyMapper.toDto(rentalCarCompany);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRentalCarCompanyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rentalCarCompanyDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rentalCarCompanyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RentalCarCompany in the database
        List<RentalCarCompany> rentalCarCompanyList = rentalCarCompanyRepository.findAll();
        assertThat(rentalCarCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRentalCarCompany() throws Exception {
        int databaseSizeBeforeUpdate = rentalCarCompanyRepository.findAll().size();
        rentalCarCompany.setId(count.incrementAndGet());

        // Create the RentalCarCompany
        RentalCarCompanyDTO rentalCarCompanyDTO = rentalCarCompanyMapper.toDto(rentalCarCompany);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRentalCarCompanyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rentalCarCompanyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RentalCarCompany in the database
        List<RentalCarCompany> rentalCarCompanyList = rentalCarCompanyRepository.findAll();
        assertThat(rentalCarCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRentalCarCompany() throws Exception {
        int databaseSizeBeforeUpdate = rentalCarCompanyRepository.findAll().size();
        rentalCarCompany.setId(count.incrementAndGet());

        // Create the RentalCarCompany
        RentalCarCompanyDTO rentalCarCompanyDTO = rentalCarCompanyMapper.toDto(rentalCarCompany);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRentalCarCompanyMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rentalCarCompanyDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RentalCarCompany in the database
        List<RentalCarCompany> rentalCarCompanyList = rentalCarCompanyRepository.findAll();
        assertThat(rentalCarCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRentalCarCompanyWithPatch() throws Exception {
        // Initialize the database
        rentalCarCompanyRepository.saveAndFlush(rentalCarCompany);

        int databaseSizeBeforeUpdate = rentalCarCompanyRepository.findAll().size();

        // Update the rentalCarCompany using partial update
        RentalCarCompany partialUpdatedRentalCarCompany = new RentalCarCompany();
        partialUpdatedRentalCarCompany.setId(rentalCarCompany.getId());

        partialUpdatedRentalCarCompany.name(UPDATED_NAME).email(UPDATED_EMAIL);

        restRentalCarCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRentalCarCompany.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRentalCarCompany))
            )
            .andExpect(status().isOk());

        // Validate the RentalCarCompany in the database
        List<RentalCarCompany> rentalCarCompanyList = rentalCarCompanyRepository.findAll();
        assertThat(rentalCarCompanyList).hasSize(databaseSizeBeforeUpdate);
        RentalCarCompany testRentalCarCompany = rentalCarCompanyList.get(rentalCarCompanyList.size() - 1);
        assertThat(testRentalCarCompany.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRentalCarCompany.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testRentalCarCompany.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void fullUpdateRentalCarCompanyWithPatch() throws Exception {
        // Initialize the database
        rentalCarCompanyRepository.saveAndFlush(rentalCarCompany);

        int databaseSizeBeforeUpdate = rentalCarCompanyRepository.findAll().size();

        // Update the rentalCarCompany using partial update
        RentalCarCompany partialUpdatedRentalCarCompany = new RentalCarCompany();
        partialUpdatedRentalCarCompany.setId(rentalCarCompany.getId());

        partialUpdatedRentalCarCompany.name(UPDATED_NAME).website(UPDATED_WEBSITE).email(UPDATED_EMAIL);

        restRentalCarCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRentalCarCompany.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRentalCarCompany))
            )
            .andExpect(status().isOk());

        // Validate the RentalCarCompany in the database
        List<RentalCarCompany> rentalCarCompanyList = rentalCarCompanyRepository.findAll();
        assertThat(rentalCarCompanyList).hasSize(databaseSizeBeforeUpdate);
        RentalCarCompany testRentalCarCompany = rentalCarCompanyList.get(rentalCarCompanyList.size() - 1);
        assertThat(testRentalCarCompany.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRentalCarCompany.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testRentalCarCompany.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    void patchNonExistingRentalCarCompany() throws Exception {
        int databaseSizeBeforeUpdate = rentalCarCompanyRepository.findAll().size();
        rentalCarCompany.setId(count.incrementAndGet());

        // Create the RentalCarCompany
        RentalCarCompanyDTO rentalCarCompanyDTO = rentalCarCompanyMapper.toDto(rentalCarCompany);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRentalCarCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, rentalCarCompanyDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rentalCarCompanyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RentalCarCompany in the database
        List<RentalCarCompany> rentalCarCompanyList = rentalCarCompanyRepository.findAll();
        assertThat(rentalCarCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRentalCarCompany() throws Exception {
        int databaseSizeBeforeUpdate = rentalCarCompanyRepository.findAll().size();
        rentalCarCompany.setId(count.incrementAndGet());

        // Create the RentalCarCompany
        RentalCarCompanyDTO rentalCarCompanyDTO = rentalCarCompanyMapper.toDto(rentalCarCompany);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRentalCarCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rentalCarCompanyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RentalCarCompany in the database
        List<RentalCarCompany> rentalCarCompanyList = rentalCarCompanyRepository.findAll();
        assertThat(rentalCarCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRentalCarCompany() throws Exception {
        int databaseSizeBeforeUpdate = rentalCarCompanyRepository.findAll().size();
        rentalCarCompany.setId(count.incrementAndGet());

        // Create the RentalCarCompany
        RentalCarCompanyDTO rentalCarCompanyDTO = rentalCarCompanyMapper.toDto(rentalCarCompany);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRentalCarCompanyMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rentalCarCompanyDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RentalCarCompany in the database
        List<RentalCarCompany> rentalCarCompanyList = rentalCarCompanyRepository.findAll();
        assertThat(rentalCarCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRentalCarCompany() throws Exception {
        // Initialize the database
        rentalCarCompanyRepository.saveAndFlush(rentalCarCompany);

        int databaseSizeBeforeDelete = rentalCarCompanyRepository.findAll().size();

        // Delete the rentalCarCompany
        restRentalCarCompanyMockMvc
            .perform(delete(ENTITY_API_URL_ID, rentalCarCompany.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RentalCarCompany> rentalCarCompanyList = rentalCarCompanyRepository.findAll();
        assertThat(rentalCarCompanyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
