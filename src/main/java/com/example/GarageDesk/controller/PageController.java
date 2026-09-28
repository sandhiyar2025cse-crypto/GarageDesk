package com.example.GarageDesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("title", "GarageDesk Dashboard");
        return "index";
    }
}