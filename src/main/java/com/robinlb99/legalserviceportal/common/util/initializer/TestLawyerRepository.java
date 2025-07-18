package com.robinlb99.legalserviceportal.common.util.initializer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robinlb99.legalserviceportal.domain.lawyer.LawyerEntity;

@Repository
public interface TestLawyerRepository extends JpaRepository<LawyerEntity, Long> {

}
