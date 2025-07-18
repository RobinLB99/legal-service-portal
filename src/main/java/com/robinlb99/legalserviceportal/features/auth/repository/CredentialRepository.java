package com.robinlb99.legalserviceportal.features.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.robinlb99.legalserviceportal.domain.credential.CredentialEntity;

@Repository
public interface CredentialRepository extends JpaRepository<CredentialEntity, Long> {

    @Query("SELECT c FROM CredentialEntity c WHERE c.username = :username")
    public Optional<CredentialEntity> findByUsername(String username);

}
