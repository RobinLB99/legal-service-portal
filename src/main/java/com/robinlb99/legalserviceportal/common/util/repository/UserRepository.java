package com.robinlb99.legalserviceportal.common.util.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.robinlb99.legalserviceportal.domain.user.UserEntity;

@NoRepositoryBean
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.idNumber = :identityNumber")
    Optional<UserEntity> findUserByIdentityNumber(String identityNumber);

    @Query("SELECT EXISTS (" +
            "SELECT 1 FROM UserEntity u WHERE u.idNumber = :identityNumber" +
            ")")
    Boolean existsUserByIdentityNumber(String identityNumber);
}
