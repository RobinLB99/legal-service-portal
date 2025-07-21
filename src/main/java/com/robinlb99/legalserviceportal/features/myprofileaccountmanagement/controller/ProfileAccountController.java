package com.robinlb99.legalserviceportal.features.myprofileaccountmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.robinlb99.legalserviceportal.common.util.dto.ProfileDTO;
import com.robinlb99.legalserviceportal.features.myprofileaccountmanagement.service.ProfileAccountService;

import jakarta.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/profile")
public class ProfileAccountController {

    private ProfileAccountService profileAccountService;

    private ProfileAccountController(ProfileAccountService profileAccountService) {
        this.profileAccountService = profileAccountService;
    }

    @GetMapping("/account")
    public String profileAccountFragment(Model model, HttpSession session) {

        ProfileDTO profileData = (ProfileDTO) session.getAttribute("profileData");
        model.addAttribute("currentProfile", profileData);

        boolean isAdmin = profileAccountService.isLawyerAdmin(profileData);

        if (isAdmin)
            model.addAttribute("isAdmin", true);

        model.addAttribute("titlePage", "Perfil de usuario");

        return "fragments/profilesettings/profile_settings";
    }

}
