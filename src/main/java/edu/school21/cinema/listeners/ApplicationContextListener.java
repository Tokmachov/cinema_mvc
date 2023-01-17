package edu.school21.cinema.listeners;

import edu.school21.cinema.config.PersistenceConfig;
import edu.school21.cinema.controllers.utils.DirectoryUtils;
import edu.school21.cinema.services.MovieService;
import edu.school21.cinema.services.PictureService;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextListener {

    private PersistenceConfig persistenceConfig;
    private MovieService movieService;
    private PictureService pictureService;

    public ApplicationContextListener(PersistenceConfig persistenceConfig,
                                      MovieService movieService,
                                      PictureService pictureService) {
        this.persistenceConfig = persistenceConfig;
        this.movieService = movieService;
        this.pictureService = pictureService;
    }

    @EventListener(classes = {ContextClosedEvent.class})
    public void performCleanUp() {
        if (persistenceConfig.isDeletePostersAfterShutdown()) {
            DirectoryUtils.delete(movieService.getPosterPath());
        }
        if (persistenceConfig.isDeleteUserPicturesAfterShutdown()) {
            DirectoryUtils.delete(pictureService.getUserPicturePath());
        }
    }
}
