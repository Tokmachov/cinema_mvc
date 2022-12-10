package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Movie;
import edu.school21.cinema.models.MovieHall;
import edu.school21.cinema.services.MovieHallService;

import edu.school21.cinema.services.MovieService;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/panel")
public class AdminPanelController {
    private static String ADMIN_PANEL_NAVIGATION_VIEW_NAME = "admin_panel_navigation";
    public static String ADMIN_PANEL_NAVIGATION_PATH = "/admin/panel";
    private static String ADMIN_PANEL_NAVIGATION_PATH_KEY = "adminPanelNavigationPathKey";
    private static String BACK_TO_ADMIN_PANEL = "Back to admin panel";
    private static String BACK_TO_ADMIN_PANEL_KEY = "backToAdminPanelKey";

    private static String MOVIE_HALLS_LIST_PATH_KEY = "movieHallsListPathKey";
    private static String MOVIE_HALLS_LIST_PATH_VALUE = "/admin/panel/halls";
    private static String MOVIE_HALLS_LIST_VIEW_NAME = "movie_halls_list";
    private static String MOVIE_HALLS_LIST_MODEL_KEY = "movieHallsListModelKey";
    private static String MOVIE_HALLS_VIEW_TITLE = "List of movie halls";
    private static String MOVIE_HALLS_VIEW_TITLE_KEY = "listOfMovieHallsTitleKey";

    private static String MOVIE_HALL_ADD_PATH_KEY = "movieHallAddPathKey";
    private static String MOVIE_HALL_ADD_PATH_VALUE = "/admin/panel/halls/add";
    private static String MOVIE_HALL_ADD_VIEW_NAME = "movie_hall_add";
    private static String MOVIE_HALL_ADD_VIEW_TITLE = "Add movie hall";
    private static String MOVIE_HALL_ADD_VIEW_TITLE_KEY = "AddMovieHallKey";

    private static String MOVIE_LIST_VIEW_NAME = "movie_list";
    private static String MOVIE_LIST_TITLE_KEY = "movieListTitleKey";
    private static String MOVIE_LIST_TITLE = "List of movies";
    private static String MOVIE_LIST_PATH_KEY = "movieListPathKey";
    private static String MOVIE_LIST_PATH = "/admin/panel/movies";
    private static String MOVIE_LIST_MODEL_KEY = "movieListModelKey";

    private static String MOVIE_ADD_VIEW_NAME = "movie_add";
    private static String MOVIE_ADD_PATH = "/admin/panel/movies/add";
    private static String MOVIE_ADD_PATH_KEY = "movieAddPathKey";
    private static String MOVIE_ADD_TITLE = "Add movie";
    private static String MOVIE_ADD_TITLE_KEY = "addMovieTitleKey";


    private static String POSTER_PATH_KEY = "posterPathKey";

    private MovieHallService movieHallService;
    private MovieService movieService;

    public AdminPanelController(MovieHallService movieHallService, MovieService movieService) {
        this.movieHallService = movieHallService;
        this.movieService = movieService;
    }

    @GetMapping
    public ModelAndView displayNavigationMenu(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ADMIN_PANEL_NAVIGATION_VIEW_NAME);

        modelAndView.addObject(MOVIE_HALLS_LIST_PATH_KEY, request.getContextPath() + "/" + MOVIE_HALLS_LIST_PATH_VALUE);
        modelAndView.addObject(MOVIE_HALLS_VIEW_TITLE_KEY, MOVIE_HALLS_VIEW_TITLE);

        modelAndView.addObject(MOVIE_HALL_ADD_PATH_KEY, request.getContextPath() + "/" + MOVIE_HALL_ADD_PATH_VALUE);
        modelAndView.addObject(MOVIE_HALL_ADD_VIEW_TITLE_KEY, MOVIE_HALL_ADD_VIEW_TITLE);

        modelAndView.addObject(MOVIE_LIST_PATH_KEY, request.getContextPath() + "/" + MOVIE_LIST_PATH);
        modelAndView.addObject(MOVIE_LIST_TITLE_KEY, MOVIE_LIST_TITLE);

        modelAndView.addObject(MOVIE_ADD_PATH_KEY, request.getContextPath() + "/" + MOVIE_ADD_PATH);
        modelAndView.addObject(MOVIE_ADD_TITLE_KEY, MOVIE_ADD_TITLE);

        return modelAndView;
    }

    @GetMapping(value = "/halls")
    public ModelAndView displayHallsListPage(HttpServletRequest request) {
        List<MovieHall> movieHallsList = movieHallService.get();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(MOVIE_HALLS_LIST_VIEW_NAME);
        modelAndView.addObject(MOVIE_HALLS_VIEW_TITLE_KEY, MOVIE_HALLS_VIEW_TITLE);
        modelAndView.addObject(MOVIE_HALLS_LIST_MODEL_KEY, movieHallsList);
        modelAndView.addObject(MOVIE_HALL_ADD_PATH_KEY, request.getContextPath() + MOVIE_HALL_ADD_PATH_VALUE);
        modelAndView.addObject(ADMIN_PANEL_NAVIGATION_PATH_KEY, request.getContextPath() + ADMIN_PANEL_NAVIGATION_PATH);
        modelAndView.addObject(BACK_TO_ADMIN_PANEL_KEY, BACK_TO_ADMIN_PANEL);
        return modelAndView;
    }

    @GetMapping("/halls/add")
    public ModelAndView displayAddHallPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(MOVIE_HALL_ADD_VIEW_NAME);
        modelAndView.addObject(MOVIE_HALL_ADD_VIEW_TITLE_KEY, MOVIE_HALL_ADD_VIEW_TITLE);
        modelAndView.addObject(MOVIE_HALLS_LIST_PATH_KEY, request.getContextPath() + MOVIE_HALLS_LIST_PATH_VALUE);
        modelAndView.addObject(ADMIN_PANEL_NAVIGATION_PATH_KEY, request.getContextPath() + ADMIN_PANEL_NAVIGATION_PATH);
        modelAndView.addObject(BACK_TO_ADMIN_PANEL_KEY, BACK_TO_ADMIN_PANEL);
        return modelAndView;
    }

    @PostMapping("/halls/add")
    public RedirectView addHall(@ModelAttribute MovieHall movieHall, HttpServletRequest request) {
        throwIfNotValid(movieHall);
        movieHallService.add(movieHall);
        return new RedirectView(request.getContextPath() + MOVIE_HALL_ADD_PATH_VALUE);
    }

    private void throwIfNotValid(MovieHall movieHall) {
        if (movieHall.getId().isEmpty() || movieHall.getNumberOfSeats() == null) {
            throw new IllegalArgumentException("Movie hall name or number of seats is not provided.");
        }
    }
    @PostMapping("/halls")
    public RedirectView deleteHallById(@RequestParam List<String> roomId, HttpServletRequest request) {
        movieHallService.deleteByIdList(roomId);
        return new RedirectView(request.getContextPath() + MOVIE_HALLS_LIST_PATH_VALUE);
    }

    @GetMapping("/movies/add")
    public ModelAndView displayMovieAddPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(MOVIE_ADD_VIEW_NAME);
        modelAndView.addObject(ADMIN_PANEL_NAVIGATION_PATH_KEY, request.getContextPath() + ADMIN_PANEL_NAVIGATION_PATH);
        modelAndView.addObject(BACK_TO_ADMIN_PANEL_KEY, BACK_TO_ADMIN_PANEL);
        return modelAndView;
    }

    @GetMapping("/movies")
    public ModelAndView displayMovieList(HttpServletRequest request) {
        List<Movie> movies = movieService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(MOVIE_LIST_VIEW_NAME);
        modelAndView.addObject(MOVIE_LIST_MODEL_KEY, movies);
        modelAndView.addObject(POSTER_PATH_KEY, movieService.getPosterPath());
        modelAndView.addObject(ADMIN_PANEL_NAVIGATION_PATH_KEY, request.getContextPath() + ADMIN_PANEL_NAVIGATION_PATH);
        modelAndView.addObject(BACK_TO_ADMIN_PANEL_KEY, BACK_TO_ADMIN_PANEL);
        return modelAndView;
    }

    @PostMapping("/movies/add")
    public RedirectView addMovie(@RequestParam MultipartFile poster, HttpServletRequest request) throws IOException {
        String title = request.getParameter("title");
        int year = Integer.parseInt(request.getParameter("year"));
        int ageRestriction = Integer.parseInt(request.getParameter("ageRestriction"));
        String description = request.getParameter("description");
        String posterId = UUID.randomUUID().toString();
        InputStream is = poster.getInputStream();
        movieService.save(new Movie(title, year, ageRestriction, description, posterId), is);
        return new RedirectView(request.getContextPath() + MOVIE_ADD_PATH);
    }

    @GetMapping("/movies/poster/{posterId}")
    public @ResponseBody byte[] getMoviePoster(@PathVariable String posterId) throws IOException {
        try (InputStream is = new FileInputStream(movieService.getPosterPath() + "/" + posterId)) {
            return IOUtils.toByteArray(is);
        }
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("msg", ex.getMessage());
        modelAndView.addObject("status", "500");
        return modelAndView;
    }
}
