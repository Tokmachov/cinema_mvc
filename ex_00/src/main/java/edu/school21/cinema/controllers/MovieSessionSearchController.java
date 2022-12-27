package edu.school21.cinema.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sessions")
public class MovieSessionSearchController {
    @GetMapping
    public ModelAndView displaySessionsView() {
        return new ModelAndView("movie_session_search");
    }
}
