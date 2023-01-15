package edu.school21.cinema.controllers;

import edu.school21.cinema.controllers.dto.UserProfileDto;
import edu.school21.cinema.controllers.mapper.UserMapper;
import edu.school21.cinema.filters.UserLoginFilter;
import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserAuthentication;
import edu.school21.cinema.services.PictureService;
import edu.school21.cinema.services.UserAuthenticationService;
import edu.school21.cinema.services.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Optional;

@Controller
@RequestMapping(UserController.PATH_BASE)
public class UserController {
    public static final String PATH_BASE = "/user";
    private static final String ADD_PICTURE_PATH_COMPONENT = "/add/picture";
    public static final String ADD_PICTURE_PATH = PATH_BASE + ADD_PICTURE_PATH_COMPONENT;
    private static final String GET_PICTURE_PATH_COMPONENT = "/picture";
    public static final String GET_PICTURE_PATH = PATH_BASE + GET_PICTURE_PATH_COMPONENT;
    private static final String USER_AUTHENTICATIONS_MODEL_KEY = "userAuthenticationsModelKey";

    private UserService userService;
    private PictureService pictureService;
    private UserAuthenticationService userAuthenticationService;

    public UserController(UserService userService, PictureService pictureService, UserAuthenticationService userAuthenticationService) {
        this.userService = userService;
        this.pictureService = pictureService;
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("/login")
    public RedirectView login(@RequestParam String userName, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("user", userName);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
        String originalRequestUri = (String) request.getServletContext().getAttribute(UserLoginFilter.ORIGINAL_REQUEST_URI_KEY);
        User user = userService.findById(userName).orElse(null);
        if (user == null) {
            user = userService.save(new User(userName));
        }
        userAuthenticationService.save(new UserAuthentication(request.getRemoteAddr(), Instant.now().toEpochMilli(), user));
        return new RedirectView(originalRequestUri);
    }

    @GetMapping("/login")
    public ModelAndView displayLoginPage() {
        ModelAndView modelAndView = new ModelAndView("login_page");
        return modelAndView;
    }

    @PostMapping(value = UserController.ADD_PICTURE_PATH_COMPONENT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserProfileDto addPicture(@RequestParam MultipartFile userPicture, @CookieValue String user) throws IOException {
        return UserMapper.toUserProfileDto(pictureService.addPicture(user, userPicture.getInputStream(), userPicture.getOriginalFilename()));
    }

    @GetMapping(value = UserController.GET_PICTURE_PATH_COMPONENT + "/{pictureId}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getPicture(@PathVariable String pictureId) throws IOException {
        try (InputStream is = new FileInputStream(pictureService.getUserPicturePath() + "/" + pictureId)) {
            return IOUtils.toByteArray(is);
        }
    }
}
