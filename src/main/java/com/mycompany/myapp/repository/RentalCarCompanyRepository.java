package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.RentalCarCompany;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RentalCarCompany entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RentalCarCompanyRepository extends JpaRepository<RentalCarCompany, Long> {}
