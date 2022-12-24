package edu.school21.cinema.listeners;

import edu.school21.cinema.config.PersistenceConfig;
import edu.school21.cinema.services.MovieService;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextListener {

    private PersistenceConfig persistenceConfig;
    private MovieService movieService;

    public ApplicationContextListener(PersistenceConfig persistenceConfig,
                                      MovieService movieService) {
        this.persistenceConfig = persistenceConfig;
        this.movieService = movieService;
    }

    @EventListener(classes = {ContextClosedEvent.class})
    public void performCleanUp() {
        if (persistenceConfig.isDeletePostersAfterShutdown()) {
            movieService.deletePosterDirectory();
        }
    }
}
