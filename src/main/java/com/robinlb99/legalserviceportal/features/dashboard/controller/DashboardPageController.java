package com.robinlb99.legalserviceportal.features.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.robinlb99.legalserviceportal.common.util.dto.ProfileDTO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DashboardPageController {

    @GetMapping("/app")
    public String goToDashboard(Model model, HttpSession session) {
        ProfileDTO profileData = (ProfileDTO) session.getAttribute("profileData");

        String titleWindow = "App | Portal de Servicio Legal";
        model.addAttribute("titleWindow", titleWindow);

        model.addAttribute("currentProfile", profileData);

        model.addAttribute("titlePage", "Dashboard");
        
        return "dashboard";
    }

}
