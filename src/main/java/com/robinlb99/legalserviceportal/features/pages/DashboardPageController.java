package com.robinlb99.legalserviceportal.features.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DashboardPageController {

    @GetMapping("/")
    public String goToDashboard(Model model) {
        log.info("Accediendo a la página del dashboard.");
        String titlePage = "App | Portal de Servicio Legal";
        model.addAttribute("titlePage", titlePage);
        return "dashboard";
    }

}
