package edu.school21.cinema.controllers.params;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieSessionParams {
    private String dateAndTime;
    private String movieHallId;
    private String movieTitle;
}
