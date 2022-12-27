package edu.school21.cinema.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movie", schema = Schema.SCHEMA)
public class Movie {
    @Id
    @Column(name = "title", length = Constants.MOVIE_TITLE_MAX_LENGTH)
    private String title;

    private int yearCreated;

    @Column(name = "age_restriction")
    private int ageRestriction;

    @Column(name = "description", length = Constants.MOVIE_DESCRIPTION_MAX_LENGTH)
    private String description;

    @Column(name = "poster_id")
    private String posterId;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<MovieSession> movieSessionList = new ArrayList<>();

    public Movie(String title, int yearCreated, int ageRestriction, String description, String posterId) {
        this.title = title;
        this.yearCreated = yearCreated;
        this.ageRestriction = ageRestriction;
        this.description = description;
        this.posterId = posterId;
    }
}
