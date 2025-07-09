package com.robinlb99.legalserviceportal.features.pages;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;

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
        log.info("Evaluando autenticación. Objeto Authentication: {}", authentication);
        if (authentication == null) {
            log.info("Authentication es nulo.");
            return false;
        }

        log.info("Authentication.isAuthenticated(): {}", authentication.isAuthenticated());
        if (!authentication.isAuthenticated()) {
            return false;
        }

        Object principal = authentication.getPrincipal();
        log.info("Principal: {} (Tipo: {})", principal, principal != null ? principal.getClass().getName() : "null");
        // Comprueba si el principal es una cadena y si su valor es "anonymousUser",
        // lo que indica que el usuario no ha iniciado sesión.
        boolean isAnonymous = principal instanceof String && "anonymousUser".equals(principal.toString());
        log.info("Es usuario anónimo: {}", isAnonymous);
        return !isAnonymous;
    }

    /**
     * Maneja las solicitudes GET a la ruta raíz ("/").
     * Redirige al usuario a la página principal de la aplicación si está
     * autenticado,
     * de lo contrario, lo redirige a la página de inicio de sesión.
     *
     * @param authentication Objeto {@link Authentication} que contiene los detalles
     *                       de la autenticación actual.
     * @return Una cadena que representa la redirección a la página de la aplicación
     *         o a la página de login.
     */
    // @GetMapping("")
    // public String rootAccess(Authentication authentication) {

    //     log.info("Acceso a la ruta '/'");
    //     if (isUserAuthenticated(authentication)) {
    //         log.info("Usuario autenticado. Redirigiendo de '/' a '/app'");
    //         return "redirect:/app";
    //     }

    //     log.info("Usuario no autenticado. Redirigiendo de '/' a '/login'");
    //     return "redirect:/login";
    // }

    /**
     * Maneja las solicitudes GET a la ruta "/login".
     * Muestra la página de inicio de sesión y maneja los mensajes de error o de
     * cierre de sesión.
     * Si el usuario ya está autenticado, lo redirige a la página principal de la
     * aplicación.
     *
     * @param error          Parámetro de solicitud opcional que indica un error de
     *                       inicio de sesión.
     * @param logout         Parámetro de solicitud opcional que indica un cierre de
     *                       sesión exitoso.
     * @param model          Objeto {@link Model} para pasar atributos a la vista.
     * @param authentication Objeto {@link Authentication} que contiene los detalles
     *                       de la autenticación actual.
     * @return El nombre de la vista de la página de login ("login-page").
     */
    @GetMapping("/login")
    public String goToLogin(
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout,
            Model model,
            Authentication authentication) {
        model.addAttribute("titlePage", "Login");

        log.info("Acceso a la ruta '/login'");
        if (isUserAuthenticated(authentication)) {
            log.info("Usuario autenticado. Redirigiendo de '/login' a '/app'");
            return "redirect:/app";
        }

        log.info("Usuario no autenticado. Accediendo a la página de login.");

        if (error != null) {
            log.warn("Error de inicio de sesión detectado.");
            model.addAttribute("errorMessage", "No se pudo iniciar sesión. Verifique sus credenciales.");
        }

        if (logout != null) {
            log.info("Cierre de sesión exitoso detectado.");
            model.addAttribute("logoutMessage", "Se cerro la sesión correctamente.");
        }

        return "login-page";
    }

}
