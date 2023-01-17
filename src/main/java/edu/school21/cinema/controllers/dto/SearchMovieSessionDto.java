package edu.school21.cinema.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchMovieSessionDto {
    private long sessionId;
    private String posterId;
    private String movieTitle;
    private String movieHallTitle;
    private String date;
    private String time;
}
