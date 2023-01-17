package edu.school21.cinema.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class DefaultPageController {
    @GetMapping
    public RedirectView displayAdminPanelNavigation(HttpServletRequest request) {
        return new RedirectView(request.getContextPath() + AdminPanelController.ADMIN_PANEL_NAVIGATION_URL);
    }
}
