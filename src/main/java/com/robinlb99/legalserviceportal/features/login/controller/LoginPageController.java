package com.robinlb99.legalserviceportal.features.login.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador para manejar las solicitudes relacionadas con la página de inicio
 * de sesión.
 * Proporciona endpoints para el acceso a la raíz y la página de login,
 * gestionando la autenticación de usuarios y la redirección.
 */
@Slf4j
@Controller
public class LoginPageController {

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
    private boolean isUserAuthenticated(Authentication authentication) {
        
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

    /**
     * Maneja las solicitudes GET a la ruta raíz ("/").
     * Redirige al usuario a la página principal de la aplicación si está
     * autenticado,
     * de lo contrario, lo redirige a la página de inicio de sesión.
     *
     * @return Una cadena que representa la redirección a la página de la aplicación
     *         o a la página de login.
     */
    @GetMapping("/")
    public String rootAccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (isUserAuthenticated(authentication)) {
            return "redirect:/app";
        }

        return "redirect:/login";
    }

    /**
     * Maneja las solicitudes GET a la ruta "/login".
     * Muestra la página de inicio de sesión y maneja los mensajes de error o de
     * cierre de sesión.
     * Si el usuario ya está autenticado, lo redirige a la página principal de la
     * aplicación.
     *
     * @param error  Parámetro de solicitud opcional que indica un error de
     *               inicio de sesión.
     * @param logout Parámetro de solicitud opcional que indica un cierre de
     *               sesión exitoso.
     * @param model  Objeto {@link Model} para pasar atributos a la vista.
     * @return El nombre de la vista de la página de login ("login-page").
     */
    @GetMapping("/login")
    public String goToLogin(
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "badCredentials", required = false) String badCredentials,
            @RequestParam(name = "logout", required = false) String logout,
            Model model) {
        model.addAttribute("titlePage", "Login");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (isUserAuthenticated(authentication)) {
            return "redirect:/app";
        }

        if (error != null) {
            model.addAttribute(
                    "errorMessage",
                    "No se pudo iniciar sesión. Inténtelo mas tarde.");
        }

        if (badCredentials != null) {
            model.addAttribute(
                    "badCredentialsMessage",
                    "El usuario o contraseña es incorrecto.");
        }

        if (logout != null) {
            model.addAttribute(
                    "logoutMessage",
                    "Se cerro la sesión correctamente.");
        }

        return "login-page";
    }
}
