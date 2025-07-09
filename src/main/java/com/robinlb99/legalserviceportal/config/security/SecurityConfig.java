package com.robinlb99.legalserviceportal.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase de configuración de seguridad para la aplicación.
 * Esta clase habilita la seguridad web y configura la cadena de filtros de
 * seguridad,
 * el codificador de contraseñas y el proveedor de autenticación.
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    /**
     * Configura la cadena de filtros de seguridad.
     *
     * @param http el {@link HttpSecurity} a configurar
     * @return el {@link SecurityFilterChain} configurado
     * @throws Exception si ocurre un error
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("Configurando la cadena de filtros de seguridad.");
        return http
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/assets/**", "/fonts/**", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/login").anonymous()
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error"))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll())
                .build();
    }

    /**
     * Crea un bean {@link PasswordEncoder} que utiliza el algoritmo de hash BCrypt.
     *
     * @return el codificador de contraseñas
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        log.info("Creando bean PasswordEncoder.");
        return new BCryptPasswordEncoder();
    }

    /**
     * Crea un bean {@link DaoAuthenticationProvider}.
     * Este proveedor es responsable de autenticar a los usuarios contra la base de
     * datos.
     *
     * @return el proveedor de autenticación
     */
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        log.info("Creando bean DaoAuthenticationProvider.");
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Expone el {@link AuthenticationManager} como un bean.
     *
     * @param config el {@link AuthenticationConfiguration}
     * @return el gestor de autenticación
     * @throws Exception si ocurre un error
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        log.info("Exponiendo AuthenticationManager como bean.");
        return config.getAuthenticationManager();
    }

}
