package com.robinlb99.legalserviceportal.domain.legalcase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalCaseRepository extends JpaRepository<LegalCaseEntity, Long> {

}
