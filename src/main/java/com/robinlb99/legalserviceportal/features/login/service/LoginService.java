package com.robinlb99.legalserviceportal.features.login.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    
    /**
     * Verifica si un usuario está autenticado.
     * Este método comprueba el estado de la autenticación y si el usuario no es
     * anónimo.
     *
     * @param authentication Objeto {@link Authentication} que contiene los detalles
     *                       de la autenticación actual.
     * @return true si el usuario está autenticado y no es un usuario anónimo, false
     *         en caso contrario.
     */
    public boolean isUserAuthenticated(Authentication authentication) {

        if (authentication == null) {
            return false;
        }

        if (!authentication.isAuthenticated()) {
            return false;
        }

        Object principal = authentication.getPrincipal();

        // Comprueba si el principal es una cadena y si su valor es "anonymousUser",
        // lo que indica que el usuario no ha iniciado sesión.
        boolean isAnonymous = principal instanceof String &&
                "anonymousUser".equals(principal.toString());
        return !isAnonymous;
    }

}
