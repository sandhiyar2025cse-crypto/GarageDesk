package com.example.GarageDesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {

    /* =========================================================
       LOGIN PAGE
    ========================================================= */

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    /* =========================================================
       LOGIN PROCESS
    ========================================================= */

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session) {

        /*
         * Demo authentication for the project.
         * The login page itself does not display these credentials.
         */

        if ("admin".equals(username)
                && "admin123".equals(password)) {

            session.setAttribute(
                    "loggedInUser",
                    username
            );

            return "redirect:/";
        }

        return "redirect:/login?error=true";
    }


    /* =========================================================
       DASHBOARD
    ========================================================= */

    @GetMapping("/")
    public String dashboard(
            HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {

            return "redirect:/login";
        }

        return "index";
    }


    /* =========================================================
       VEHICLES PAGE
    ========================================================= */

    @GetMapping("/vehicles")
    public String vehiclesPage(
            HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {

            return "redirect:/login";
        }

        return "vehicles";
    }


    /* =========================================================
       JOB CARDS PAGE
    ========================================================= */

    @GetMapping("/jobcards")
    public String jobCardsPage(
            HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {

            return "redirect:/login";
        }

        return "jobcards";
    }


    /* =========================================================
       SERVICE BAYS PAGE
    ========================================================= */

    @GetMapping("/servicebays")
    public String serviceBaysPage(
            HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {

            return "redirect:/login";
        }

        return "servicebays";
    }


    /* =========================================================
       MECHANICS PAGE
    ========================================================= */

    @GetMapping("/mechanics")
    public String mechanicsPage(
            HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {

            return "redirect:/login";
        }

        return "mechanics";
    }


    /* =========================================================
       BILLS PAGE
    ========================================================= */

    @GetMapping("/bills")
    public String billsPage(
            HttpSession session) {

        if (session.getAttribute("loggedInUser") == null) {

            return "redirect:/login";
        }

        return "bills";
    }


    /* =========================================================
       LOGOUT
    ========================================================= */

    @GetMapping("/logout")
    public String logout(
            HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }

}