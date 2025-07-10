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

@Slf4j
@Service
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private CredentialRepository credentialRepository;
    private UserRepository userRepository;

    public CustomAuthenticationSuccessHandler(CredentialRepository credentialRepository,
            UserRepository userRepository) {
        this.credentialRepository = credentialRepository;
        this.userRepository = userRepository;
    }

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

        response.sendRedirect("/");
    }

}
