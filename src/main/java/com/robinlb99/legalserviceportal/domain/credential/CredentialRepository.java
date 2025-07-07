package com.robinlb99.legalserviceportal.domain.credential;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<CredentialEntity, Long> {

}
