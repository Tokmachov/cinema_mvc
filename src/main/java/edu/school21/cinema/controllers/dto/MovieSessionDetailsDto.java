package edu.school21.cinema.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MovieSessionDetailsDto {
    private String movieHallTitle;
    private long numberOfSeats;
    private String date;
    private String time;
    private String movieTitle;
    private long yearCreated;
    private long ageRestriction;
    private String posterId;
    private String movieDescription;
}
