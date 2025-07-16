package com.robinlb99.legalserviceportal.config.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.robinlb99.legalserviceportal.common.util.dto.ProfileDTO;
import com.robinlb99.legalserviceportal.domain.credential.CredentialEntity;
import com.robinlb99.legalserviceportal.domain.credential.CredentialRepository;
import com.robinlb99.legalserviceportal.domain.credential.dto.CredentialDTO;
import com.robinlb99.legalserviceportal.domain.user.UserEntity;
import com.robinlb99.legalserviceportal.domain.user.UserRepository;
import com.robinlb99.legalserviceportal.domain.user.dto.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

/**
 * Manejador personalizado para el éxito de la autenticación.
 *
 * Se ejecuta cuando un usuario inicia sesión correctamente. Su principal
 * responsabilidad es
 * recopilar la información esencial del usuario y almacenarla en la sesión
 * HTTP
 * para un acceso rápido en las solicitudes posteriores.
 */
@Slf4j
@Service
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private CredentialRepository credentialRepository;
    private UserRepository userRepository;

    /**
     * Construye el manejador de éxito de autenticación con las dependencias
     * necesarias.
     *
     * @param credentialRepository Repositorio para acceder a los datos de las
     *                             credenciales.
     * @param userRepository       Repositorio para acceder a los datos del
     *                             usuario.
     */
    public CustomAuthenticationSuccessHandler(CredentialRepository credentialRepository,
            UserRepository userRepository) {
        this.credentialRepository = credentialRepository;
        this.userRepository = userRepository;
    }

    /**
     * Crea un objeto ProfileDTO para almacenar en caché los datos del perfil del
     * usuario.
     *
     * Este método extrae la información necesaria de las entidades CredentialEntity
     * y UserEntity
     * y la empaqueta en un ProfileDTO para un uso eficiente en la sesión.
     *
     * @param credentials La entidad de credenciales del usuario autenticado.
     * @param user        La entidad de usuario del usuario autenticado.
     * @return Un ProfileDTO que contiene los datos combinados del perfil.
     */
    private ProfileDTO cacheProfile(CredentialEntity credentials, UserEntity user) {

        CredentialDTO credentialDTO = new CredentialDTO();
        credentialDTO.setUsername(credentials.getUsername());
        credentialDTO.setAuthorities(credentials.getAuthorities());

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setSurnames(user.getSurnames());
        userDTO.setEmail(user.getEmail());

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setCredentials(credentialDTO);
        profileDTO.setUserData(userDTO);

        return profileDTO;
    }

    /**
     * Se invoca cuando un usuario ha sido autenticado con éxito.
     *
     * Este método obtiene los detalles del usuario de la base de datos, almacena
     * los datos del perfil
     * en la sesión HTTP y redirige al usuario a la página principal de la
     * aplicación.
     *
     * @param request        El objeto HttpServletRequest.
     * @param response       El objeto HttpServletResponse.
     * @param authentication El objeto Authentication que contiene los detalles del
     *                       principal.
     * @throws IOException      Si ocurre un error de entrada/salida.
     * @throws ServletException Si ocurre un error de servlet.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        Optional<CredentialEntity> credentials = credentialRepository.findByUsername(
                authentication.getName());
        // log.info("Is credentials present?: " + (credentials.isPresent() ? "YES" :
        // "NO"));

        Optional<UserEntity> user = userRepository.findById(
                credentials.get().getUser().getId());

        HttpSession session = request.getSession();
        session.setAttribute(
                "profileData",
                cacheProfile(credentials.get(), user.get()));

        response.sendRedirect("/app");
    }

}
