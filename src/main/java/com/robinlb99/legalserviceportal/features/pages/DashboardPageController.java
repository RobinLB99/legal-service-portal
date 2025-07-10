package com.robinlb99.legalserviceportal.features.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.robinlb99.legalserviceportal.common.util.dto.ProfileDTO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DashboardPageController {

    @GetMapping("/")
    public String goToDashboard(Model model, HttpSession session) {
        // log.info("Accediendo a la página del dashboard.");

        String titlePage = "App | Portal de Servicio Legal";
        model.addAttribute("titlePage", titlePage);

        ProfileDTO profileData = (ProfileDTO) session.getAttribute("profileData");
        model.addAttribute("currentProfile", profileData);

        return "dashboard";
    }

}
