package com.robinlb99.legalserviceportal.config.security;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Manejador personalizado para el fallo de la autenticación.
 *
 * Se activa cuando un intento de inicio de sesión falla. Esta clase determina
 * la causa del fallo
 * y redirige al usuario de vuelta a la página de inicio de sesión con un
 * parámetro de URL adecuado
 * para mostrar un mensaje de error relevante.
 */
@Service
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /**
     * Se invoca cuando un intento de autenticación falla.
     *
     * Este método examina la {@link AuthenticationException} para determinar la
     * naturaleza del fallo.
     * Si el fallo se debe a credenciales incorrectas (por ejemplo, contraseña
     * incorrecta), redirige a
     * {@code /login?badCredentials}. Para todos los demás tipos de fallos de
     * autenticación,
     * redirige a {@code /login?error}.
     *
     * @param request   El objeto HttpServletRequest.
     * @param response  El objeto HttpServletResponse.
     * @param exception La excepción lanzada durante el fallo de la autenticación.
     * @throws IOException      Si ocurre un error de entrada/salida durante la
     *                          redirección.
     * @throws ServletException Si ocurre un error de servlet.
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        String redirectUri = "/login?";

        if (exception instanceof BadCredentialsException)
            redirectUri += "badCredentials";

        else
            redirectUri += "error";

        response.sendRedirect(redirectUri);

    }

}
