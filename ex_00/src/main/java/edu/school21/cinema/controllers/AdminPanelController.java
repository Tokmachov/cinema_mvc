package edu.school21.cinema.controllers;

import edu.school21.cinema.controllers.dto.MovieSessionDto;
import edu.school21.cinema.controllers.mapper.MovieSessionMapper;
import edu.school21.cinema.controllers.params.MovieSessionParams;
import edu.school21.cinema.models.*;
import edu.school21.cinema.services.MovieHallService;

import edu.school21.cinema.services.MovieService;
import edu.school21.cinema.services.MovieSessionService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/panel")
public class AdminPanelController {
    private static String ADMIN_PANEL_NAVIGATION_VIEW_NAME = "admin_panel_navigation";
    public static String ADMIN_PANEL_NAVIGATION_URL = "/admin/panel";
    private static String ADMIN_PANEL_NAVIGATION_URL_KEY = "adminPanelNavigationPathKey";
    private static String BACK_TO_ADMIN_PANEL_MSG = "Back to admin panel";
    private static String BACK_TO_ADMIN_PANEL_MSG_KEY = "backToAdminPanelKey";

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

    private static String SESSION_ADD_VIEW_NAME = "movie_session_add";
    private static final String SESSION_ADD_PATH = "/admin/panel/sessions/add";
    private static final String SESSION_ADD_PATH_KEY = "sessionAddPathKey";

    private static String SESSION_ADD_TITLE = "Add session";
    private static String SESSION_ADD_TITLE_KEY = "sessionAddTitleKey";

    private static String MOVIE_SESSION_LIST_MODEL_KEY = "movieSessionListModelKey";
    private static String MOVIE_SESSION_LIST_VIEW_NAME = "movie_session_list";
    private static String MOVIE_SESSION_LIST_TITLE = "Movie session list";
    private static String MOVIE_SESSION_LIST_TITLE_KEY = "movieSessionListTitleKey";
    private static String MOVIE_SESSION_LIST_URL_KEY = "movieSessionListUrlKey";
    private static String MOVIE_SESSION_LIST_URL = "/admin/panel/sessions/";

    private static String CONSTANTS_KEY = "constantsKey";

    private MovieHallService movieHallService;
    private MovieService movieService;
    private MovieSessionService movieSessionService;

    public AdminPanelController(MovieHallService movieHallService,
                                MovieService movieService,
                                MovieSessionService movieSessionService) {
        this.movieHallService = movieHallService;
        this.movieService = movieService;
        this.movieSessionService = movieSessionService;
    }

    @GetMapping
    public ModelAndView displayNavigationMenu(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ADMIN_PANEL_NAVIGATION_VIEW_NAME);

        modelAndView.addObject(MOVIE_HALLS_LIST_PATH_KEY, request.getContextPath() + MOVIE_HALLS_LIST_PATH_VALUE);
        modelAndView.addObject(MOVIE_HALLS_VIEW_TITLE_KEY, MOVIE_HALLS_VIEW_TITLE);

        modelAndView.addObject(MOVIE_HALL_ADD_PATH_KEY, request.getContextPath() + MOVIE_HALL_ADD_PATH_VALUE);
        modelAndView.addObject(MOVIE_HALL_ADD_VIEW_TITLE_KEY, MOVIE_HALL_ADD_VIEW_TITLE);

        modelAndView.addObject(MOVIE_LIST_PATH_KEY, request.getContextPath() + MOVIE_LIST_PATH);
        modelAndView.addObject(MOVIE_LIST_TITLE_KEY, MOVIE_LIST_TITLE);

        modelAndView.addObject(MOVIE_ADD_PATH_KEY, request.getContextPath() + MOVIE_ADD_PATH);
        modelAndView.addObject(MOVIE_ADD_TITLE_KEY, MOVIE_ADD_TITLE);

        modelAndView.addObject(SESSION_ADD_PATH_KEY, request.getContextPath() + SESSION_ADD_PATH);
        modelAndView.addObject(SESSION_ADD_TITLE_KEY, SESSION_ADD_TITLE);

        modelAndView.addObject(MOVIE_SESSION_LIST_TITLE_KEY, MOVIE_SESSION_LIST_TITLE);
        modelAndView.addObject(MOVIE_SESSION_LIST_URL_KEY, request.getContextPath() + MOVIE_SESSION_LIST_URL);

        return modelAndView;
    }

    @GetMapping(value = "/halls")
    public ModelAndView displayHallsListPage(HttpServletRequest request) {
        List<MovieHall> movieHallsList = movieHallService.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(MOVIE_HALLS_LIST_VIEW_NAME);
        modelAndView.addObject(MOVIE_HALLS_VIEW_TITLE_KEY, MOVIE_HALLS_VIEW_TITLE);
        modelAndView.addObject(MOVIE_HALLS_LIST_MODEL_KEY, movieHallsList);
        modelAndView.addObject(MOVIE_HALL_ADD_PATH_KEY, request.getContextPath() + MOVIE_HALL_ADD_PATH_VALUE);
        modelAndView.addObject(ADMIN_PANEL_NAVIGATION_URL_KEY, request.getContextPath() + ADMIN_PANEL_NAVIGATION_URL);
        modelAndView.addObject(BACK_TO_ADMIN_PANEL_MSG_KEY, BACK_TO_ADMIN_PANEL_MSG);
        return modelAndView;
    }

    @GetMapping("/halls/add")
    public ModelAndView displayAddHallPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(MOVIE_HALL_ADD_VIEW_NAME);
        modelAndView.addObject(MOVIE_HALL_ADD_VIEW_TITLE_KEY, MOVIE_HALL_ADD_VIEW_TITLE);
        modelAndView.addObject(MOVIE_HALLS_LIST_PATH_KEY, request.getContextPath() + MOVIE_HALLS_LIST_PATH_VALUE);
        modelAndView.addObject(ADMIN_PANEL_NAVIGATION_URL_KEY, request.getContextPath() + ADMIN_PANEL_NAVIGATION_URL);
        modelAndView.addObject(BACK_TO_ADMIN_PANEL_MSG_KEY, BACK_TO_ADMIN_PANEL_MSG);
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
    public RedirectView deleteHallById(@RequestParam(required = false, defaultValue = "") List<String> roomId, HttpServletRequest request) {
        movieHallService.deleteByIdList(roomId);
        return new RedirectView(request.getContextPath() + MOVIE_HALLS_LIST_PATH_VALUE);
    }

    @GetMapping("/movies/add")
    public ModelAndView displayMovieAddPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(MOVIE_ADD_VIEW_NAME);
        modelAndView.addObject(ADMIN_PANEL_NAVIGATION_URL_KEY, request.getContextPath() + ADMIN_PANEL_NAVIGATION_URL);
        modelAndView.addObject(BACK_TO_ADMIN_PANEL_MSG_KEY, BACK_TO_ADMIN_PANEL_MSG);
        modelAndView.addObject(CONSTANTS_KEY, new Constants());
        return modelAndView;
    }

    @GetMapping("/movies")
    public ModelAndView displayMovieList(HttpServletRequest request) {
        List<Movie> movies = movieService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(MOVIE_LIST_VIEW_NAME);
        modelAndView.addObject(MOVIE_LIST_MODEL_KEY, movies);
        modelAndView.addObject(POSTER_PATH_KEY, movieService.getPosterPath());
        modelAndView.addObject(ADMIN_PANEL_NAVIGATION_URL_KEY, request.getContextPath() + ADMIN_PANEL_NAVIGATION_URL);
        modelAndView.addObject(BACK_TO_ADMIN_PANEL_MSG_KEY, BACK_TO_ADMIN_PANEL_MSG);
        return modelAndView;
    }

    @PostMapping("/movies")
    public RedirectView deleteMovies(@RequestParam(required = false, defaultValue = "") List<String> movieIdList, HttpServletRequest request) {
        movieService.deleteByMovieTitleList(movieIdList);
        return new RedirectView(request.getContextPath() + "/admin/panel/movies");
    }

    @PostMapping("/movies/add")
    public RedirectView addMovie(@RequestParam MultipartFile poster, HttpServletRequest request) throws IOException {
        Movie movie = parseMovie(request);
        InputStream is = poster.getInputStream();
        movieService.save(movie, is);
        return new RedirectView(request.getContextPath() + MOVIE_ADD_PATH);
    }

    @GetMapping("/movies/poster/{posterId}")
    public @ResponseBody byte[] getMoviePoster(@PathVariable String posterId) throws IOException {
        try (InputStream is = new FileInputStream(movieService.getPosterPath() + "/" + posterId)) {
            return IOUtils.toByteArray(is);
        }
    }

    @GetMapping("/sessions/add")
    public ModelAndView displaySessionAddPage(HttpServletRequest request) {
        List<Movie> movieList = movieService.findAll();
        List<MovieHall> movieHallList = movieHallService.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(SESSION_ADD_VIEW_NAME);
        modelAndView.addObject(SESSION_ADD_TITLE_KEY, SESSION_ADD_TITLE);
        modelAndView.addObject(BACK_TO_ADMIN_PANEL_MSG_KEY, BACK_TO_ADMIN_PANEL_MSG);
        modelAndView.addObject(ADMIN_PANEL_NAVIGATION_URL_KEY, request.getContextPath() + ADMIN_PANEL_NAVIGATION_URL);
        modelAndView.addObject(MOVIE_LIST_MODEL_KEY, movieList);
        modelAndView.addObject(MOVIE_HALLS_LIST_MODEL_KEY, movieHallList);
        return modelAndView;
    }

    @PostMapping("/sessions/add")
    public RedirectView addSession(@ModelAttribute MovieSessionParams sessionParams, HttpServletRequest request) {
        movieSessionService.saveMovieSession(sessionParams);
        return new RedirectView(request.getContextPath() + SESSION_ADD_PATH);
    }

    @GetMapping("/sessions")
    public ModelAndView displayMovieSessionList(HttpServletRequest request) {
        List<MovieSession> movieSessionList = movieSessionService.findAll();
        List<MovieSessionDto> movieSessionDtoList = movieSessionList
                .stream()
                .map(MovieSessionMapper::toDto)
                .collect(Collectors.toList());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(MOVIE_SESSION_LIST_VIEW_NAME);
        modelAndView.addObject(MOVIE_SESSION_LIST_MODEL_KEY, movieSessionDtoList);
        modelAndView.addObject(MOVIE_SESSION_LIST_TITLE_KEY, MOVIE_SESSION_LIST_TITLE);
        modelAndView.addObject(ADMIN_PANEL_NAVIGATION_URL_KEY, request.getContextPath() +  ADMIN_PANEL_NAVIGATION_URL);
        modelAndView.addObject(BACK_TO_ADMIN_PANEL_MSG_KEY,BACK_TO_ADMIN_PANEL_MSG);
        return modelAndView;
    }

    @PostMapping("/sessions")
    public RedirectView deleteMovieSessions(@RequestParam(required = false, defaultValue = "") List<Long> movieSessionIdList, HttpServletRequest request) {
        movieSessionService.deleteByIds(movieSessionIdList);
        return new RedirectView(request.getContextPath() + MOVIE_SESSION_LIST_URL);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("msg", ex.getMessage());
        modelAndView.addObject("status", "500");
        return modelAndView;
    }

    private static Movie parseMovie(HttpServletRequest request) {
        String title = request.getParameter("title");
        String year = request.getParameter("year");
        String ageRestriction = request.getParameter("ageRestriction");
        String description = request.getParameter("description");
        String posterId = UUID.randomUUID().toString();

        if (StringUtils.isAnyEmpty(title, year, ageRestriction, description, posterId)) {
            throw new IllegalArgumentException("Some of the movie parameters are absent.");
        }

        try {
            int intYear = Integer.parseInt(request.getParameter("year"));
            int intAgeRestriction = Integer.parseInt(request.getParameter("ageRestriction"));
            return new Movie(title, intYear, intAgeRestriction, description, posterId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Year or Age restriction must be presented by integers.");
        }
    }
}
