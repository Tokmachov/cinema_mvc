package edu.school21.cinema.models;

import lombok.Getter;

@Getter
public class Constants {
    public static final int MOVIE_TITLE_MAX_LENGTH = 40;
    public static final int MOVIE_DESCRIPTION_MAX_LENGTH = 200;

    public int getMovieTitleLength() {
        return MOVIE_TITLE_MAX_LENGTH;
    }

    public int getMovieDescriptionMaxLength() {
        return MOVIE_DESCRIPTION_MAX_LENGTH;
    }
}
