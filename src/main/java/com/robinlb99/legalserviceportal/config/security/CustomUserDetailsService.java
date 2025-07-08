package com.robinlb99.legalserviceportal.config.security;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.robinlb99.legalserviceportal.domain.credential.CredentialEntity;
import com.robinlb99.legalserviceportal.domain.credential.CredentialRepository;
import com.robinlb99.legalserviceportal.domain.credential.Permission;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CredentialRepository credentialRepository;

    public CustomUserDetailsService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    private UserDetails createUserDetails(CredentialEntity credencial) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        String rol = credencial.getAuthorities().getRol().name();
        List<Permission> permissions = credencial.getAuthorities().getPermissions();

        authorities.add(
                new SimpleGrantedAuthority("ROLE_" + rol.toUpperCase()));

        if (!permissions.isEmpty()) {
            for (Permission permission : permissions) {
                authorities.add(
                        new SimpleGrantedAuthority(permission.name().toUpperCase() + "_PERMISSION"));
            }
        }

        log.info("ROLES Y PERMISOS: " + authorities.toString());

        return User.builder()
                .username(credencial.getUsername())
                .password(credencial.getPasswordHash())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!credencial.isActive())
                .build();

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CredentialEntity> credential = credentialRepository.findByUsername(username);

        if (!credential.isPresent())
            throw new UsernameNotFoundException("❌ ¡Credenciales no encontradas!");

        return createUserDetails(credential.get());
    }

}
