package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.AirlineCompany;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AirlineCompany entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AirlineCompanyRepository extends JpaRepository<AirlineCompany, Long> {}
