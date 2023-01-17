package edu.school21.cinema.controllers.mapper;

import edu.school21.cinema.controllers.dto.MovieChatMessageDto;
import edu.school21.cinema.models.MovieChatMessage;

public class MovieChatMessageMapper {
    public static MovieChatMessageDto toDto(MovieChatMessage movieChatMessage) {
        return new MovieChatMessageDto(movieChatMessage.getText(), movieChatMessage.getUser().getName());
    }
}
