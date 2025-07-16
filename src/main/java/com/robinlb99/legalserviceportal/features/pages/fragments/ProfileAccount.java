package com.robinlb99.legalserviceportal.features.pages.fragments;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.robinlb99.legalserviceportal.common.util.dto.ProfileDTO;
import com.robinlb99.legalserviceportal.domain.credential.dto.AuthoritiesDTO;
import com.robinlb99.legalserviceportal.domain.credential.enums.Permission;
import com.robinlb99.legalserviceportal.domain.credential.enums.Role;

import jakarta.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/profile")
public class ProfileAccount {

    @GetMapping("/account")
    public String profileAccountFragment(Model model, HttpSession session) {

        ProfileDTO profileData = (ProfileDTO) session.getAttribute("profileData");
        model.addAttribute("currentProfile", profileData);

        AuthoritiesDTO authorities = profileData.getCredentials().getAuthorities();
        if (authorities.getRol().equals(Role.LAWYER)) {
            boolean isAdmin = authorities.getPermissions().stream()
                    .anyMatch(permission -> permission.equals(Permission.ADMIN));
            if (isAdmin)
                model.addAttribute("isAdmin", "isAdmin");
        }

        model.addAttribute("titlePage", "Perfil de usuario");

        return "fragments/profilesettings/profile_settings";
    }

}
