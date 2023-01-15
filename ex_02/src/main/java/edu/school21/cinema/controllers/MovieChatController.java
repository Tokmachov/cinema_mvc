package edu.school21.cinema.controllers;
import edu.school21.cinema.controllers.dto.MovieChatMessageDto;
import edu.school21.cinema.controllers.dto.UserAuthenticationDto;
import edu.school21.cinema.controllers.mapper.MovieChatMessageMapper;
import edu.school21.cinema.controllers.mapper.UserAuthenticationMapper;
import edu.school21.cinema.models.Movie;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.MovieChatMessageService;
import edu.school21.cinema.services.MovieService;
import edu.school21.cinema.services.UserAuthenticationService;
import edu.school21.cinema.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/movies")
public class MovieChatController {

    private MovieChatMessageService movieChatMessageService;
    private MovieService movieService;
    private UserService userService;
    private UserAuthenticationService userAuthenticationService;

    public static final String PATH_BASE = "/movies";
    private static final String MOVIE_MODEL_KEY = "movieModelKey";
    private static final String USER_MODEL_KEY = "userModelKey";

    public static final String MOVIE_CHAT_MESSAGES_MODEL_KEY = "movieChatMessagesModelKey";

    public static final String getMoviesAbsoluteUrlBase(String contextPath) {
        return contextPath + PATH_BASE;
    }
    private static final String MOVIE_CHAT_PAGE_NAME = "movie_chat";

    private static final String ADD_USER_PICTURE_PATH_KEY = "addUserPicturePathKey";
    private static final String GET_USER_PICTURE_PATH_KEY = "getUserPicturePathKey";
    private static final String USER_AUTHENTICATIONS_MODEL_KEY = "userAuthenticationsModelKey";

    public MovieChatController(MovieChatMessageService movieChatMessageService,
                               MovieService movieService,
                               UserService userService,
                               UserAuthenticationService userAuthenticationService) {
        this.movieChatMessageService = movieChatMessageService;
        this.movieService = movieService;
        this.userService = userService;
        this.userAuthenticationService = userAuthenticationService;
    }

    @GetMapping
    public String showMovieList() {
        return "forward:" + "/admin/panel/movies";
    }

    @GetMapping("/{movieId}/chat")
    public ModelAndView displayMovieChat(@PathVariable String movieId, @CookieValue("user") String userName, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView(MOVIE_CHAT_PAGE_NAME);
        Movie movie = movieService.findById(Long.parseLong(movieId));
        User user = userService.findByIdLoadWithPictures(userName).orElseThrow(() -> new IllegalArgumentException("Failed to find user with name: " + userName));
        List<UserAuthenticationDto> userAuthenticationList = userAuthenticationService.findAllByUserId(user.getName())
                .stream()
                .map(UserAuthenticationMapper::toDto)
                .collect(Collectors.toList());

        modelAndView.addObject(MOVIE_MODEL_KEY, movie);
        modelAndView.addObject(USER_MODEL_KEY, user);
        modelAndView.addObject(ADD_USER_PICTURE_PATH_KEY, httpServletRequest.getContextPath() + UserController.ADD_PICTURE_PATH);
        modelAndView.addObject(GET_USER_PICTURE_PATH_KEY, httpServletRequest.getContextPath() + UserController.GET_PICTURE_PATH);
        modelAndView.addObject(USER_AUTHENTICATIONS_MODEL_KEY, userAuthenticationList);
        return modelAndView;
    }

    @GetMapping(value = "/{movieId}/chat/message", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<MovieChatMessageDto> getList(@PathVariable String movieId) {
        return movieChatMessageService.findLastOrderedByDate(Long.parseLong(movieId), 20)
                .stream()
                .map(MovieChatMessageMapper::toDto)
                .collect(Collectors.toList());
    }

    //all messages that will be sent using websocket to url /app/movies/{movieId}/chat/message will come here
    //in order to specify url to which we send messages in message broker we can use annotation @SendTo("/topic/someurl")
    //or if we don't specify @SendTo then the messages are sent to /topic/movies/{movieId}/chat/message
    // so subscribes must subscribe to /topic/movies/{movieId}/chat/message or someurl if @SendTo specified.
    @MessageMapping("/movies/{movieId}/chat/message")
    public MovieChatMessageDto send(MovieChatMessageDto movieChatMessageDto, @DestinationVariable String movieId) throws Exception {
        movieChatMessageService.save(movieChatMessageDto.getUserName(), movieId, movieChatMessageDto.getText());
        return movieChatMessageDto;
    }
}
