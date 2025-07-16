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
import com.robinlb99.legalserviceportal.domain.credential.enums.Permission;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementación personalizada de {@link UserDetailsService} para la
 * autenticación de Spring Security.
 *
 * Esta clase se encarga de cargar los detalles específicos del usuario desde la
 * base de datos
 * a partir de un nombre de usuario. Convierte la {@link CredentialEntity} del
 * dominio
 * en un objeto {@link UserDetails} que Spring Security puede entender y
 * utilizar para
 * la autenticación y autorización.
 */
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CredentialRepository credentialRepository;

    /**
     * Construye el servicio con el repositorio de credenciales necesario.
     *
     * @param credentialRepository El repositorio para acceder a los datos de las
     *                             credenciales.
     */
    public CustomUserDetailsService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    /**
     * Convierte una {@link CredentialEntity} en un objeto {@link UserDetails} de
     * Spring Security.
     *
     * Este método mapea el rol y los permisos de la entidad de credenciales a un
     * conjunto de
     * {@link GrantedAuthority}. También establece el estado de la cuenta del
     * usuario (activa, bloqueada, etc.).
     *
     * @param credencial La entidad de credenciales del usuario.
     * @return Un objeto {@link UserDetails} que representa al usuario para Spring
     *         Security.
     */
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

        // log.info("ROLES Y PERMISOS: " + authorities.toString());

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

    /**
     * Carga un usuario por su nombre de usuario.
     *
     * Este es el método principal requerido por la interfaz
     * {@link UserDetailsService}.
     * Busca una credencial en la base de datos utilizando el nombre de usuario
     * proporcionado.
     * Si se encuentra, la convierte en un objeto {@link UserDetails}. Si no, lanza
     * una
     * {@link UsernameNotFoundException}.
     *
     * @param username El nombre de usuario que se está intentando autenticar.
     * @return Un objeto {@link UserDetails} si el usuario es encontrado.
     * @throws UsernameNotFoundException si no se encuentra ningún usuario con el
     *                                   nombre de usuario proporcionado.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CredentialEntity> credential = credentialRepository.findByUsername(username);

        if (!credential.isPresent())
            throw new UsernameNotFoundException("❌ ¡Credenciales no encontradas!");

        return createUserDetails(credential.get());
    }

}
