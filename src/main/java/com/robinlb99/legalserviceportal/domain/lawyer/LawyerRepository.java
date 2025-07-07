package com.robinlb99.legalserviceportal.domain.lawyer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LawyerRepository extends JpaRepository<LawyerEntity, Long> {

}
