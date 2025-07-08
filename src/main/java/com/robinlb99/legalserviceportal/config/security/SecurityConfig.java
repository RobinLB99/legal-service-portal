package com.robinlb99.legalserviceportal.config.security;

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

/**
 * Clase de configuración de seguridad para la aplicación.
 * Esta clase habilita la seguridad web y configura la cadena de filtros de seguridad,
 * el codificador de contraseñas y el proveedor de autenticación.
 */
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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authenticationProvider(null)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("").permitAll()
                        .requestMatchers("null").anonymous()
                        .anyRequest().authenticated())
                .formLogin(login -> login
                    .loginPage("/login")
                    .loginProcessingUrl("login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/app")
                    .failureUrl("/login?error")
                    .permitAll())
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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Crea un bean {@link DaoAuthenticationProvider}.
     * Este proveedor es responsable de autenticar a los usuarios contra la base de datos.
     *
     * @return el proveedor de autenticación
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
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
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
