package com.robinlb99.legalserviceportal.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.idNumber = :identityNumber")
    Optional<UserEntity> findUserByIdentityNumber(String identityNumber);

    @Query("SELECT EXISTS (" +
            "SELECT 1 FROM UserEntity u WHERE u.idNumber = :identityNumber" +
            ")")
    Boolean existsUserByIdentityNumber(String identityNumber);
}
