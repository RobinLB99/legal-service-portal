package com.robinlb99.legalserviceportal.common.util.initializer;

import com.robinlb99.legalserviceportal.common.util.repository.UserRepository;
import com.robinlb99.legalserviceportal.domain.credential.Authorities;
import com.robinlb99.legalserviceportal.domain.credential.CredentialEntity;
import com.robinlb99.legalserviceportal.domain.credential.enums.Permission;
import com.robinlb99.legalserviceportal.domain.credential.enums.Role;
import com.robinlb99.legalserviceportal.domain.lawyer.LawyerEntity;
import com.robinlb99.legalserviceportal.domain.user.UserEntity;
import com.robinlb99.legalserviceportal.features.auth.repository.CredentialRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TestLawyerRepository lawyerRepository;
    private final CredentialRepository credentialRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (userRepository.findUserByIdentityNumber("1122334455").isEmpty()) {
            // 1. Crear y guardar el usuario
            UserEntity user = new UserEntity(
                    "Alex",
                    "J.",
                    "Morgan",
                    "1122334455",
                    LocalDate.of(1988, 5, 20),
                    "alex.morgan.lawyer@example.com",
                    "+15558889999"
            );
            userRepository.save(user);

            // 2. Crear y guardar el abogado
            LawyerEntity lawyer = new LawyerEntity();
            lawyer.setUser(user);
            lawyer.setLicence("LIC-2025-0001");
            lawyer.setSpecialization("Derecho de Familia");
            lawyerRepository.save(lawyer);

            // 3. Crear y guardar las credenciales
            CredentialEntity credential = new CredentialEntity();
            credential.setUser(user);
            credential.setUsername("lawyer.alex");
            credential.setPasswordHash(passwordEncoder.encode("password123"));
            credential.setActive(true);

            Authorities authorities = new Authorities();
            authorities.setRol(Role.LAWYER);
            authorities.setPermissions(List.of(Permission.ADMIN));
            credential.setAuthorities(authorities);

            credentialRepository.save(credential);
        }
    }
}
