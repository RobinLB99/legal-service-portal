package com.robinlb99.legalserviceportal.features.myprofileaccountmanagement.service;

import org.springframework.stereotype.Service;

import com.robinlb99.legalserviceportal.common.util.dto.ProfileDTO;
import com.robinlb99.legalserviceportal.domain.credential.Authorities;
import com.robinlb99.legalserviceportal.domain.credential.enums.Permission;
import com.robinlb99.legalserviceportal.domain.credential.enums.Role;

@Service
public class ProfileAccountService {

    /**
     * Verifica si el perfil de usuario corresponde a un abogado con permisos de
     * administrador.
     * 
     * @param profileData El DTO del perfil del usuario.
     * @return true si es un abogado administrador, false en caso contrario.
     */
    public boolean isLawyerAdmin(ProfileDTO profileData) {
        if (profileData == null || profileData.getCredentials() == null) {
            return false;
        }

        Authorities authorities = profileData.getCredentials().getAuthorities();
        boolean isLawyer = authorities.getRol().equals(Role.LAWYER);
        boolean isAdmin = authorities.getPermissions().stream()
                .anyMatch(permission -> permission.equals(Permission.ADMIN));
                
        return isLawyer && isAdmin;
    }

}
