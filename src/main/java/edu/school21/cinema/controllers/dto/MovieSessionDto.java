package edu.school21.cinema.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MovieSessionDto {
    private long id;
    private String date;
    private String time;
    private String movieTitle;
    private String movieHallName;
    private int price;
}
