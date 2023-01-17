package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "movie_session", schema = Schema.SCHEMA)
public class MovieSession {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "time_start", nullable = false)
    private long timeStart;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false, foreignKey = @ForeignKey(name = "fk_movie_session__movie"))
    private Movie movie;

    private int price;

    @ManyToOne
    @JoinColumn(name = "movie_hall_id", nullable = true, foreignKey = @ForeignKey(name = "fk_movie_session__movie_hall"))
    private MovieHall movieHall;

    public MovieSession(long timeStart, Movie movie, int price, MovieHall movieHall) {
        this.timeStart = timeStart;
        this.movie = movie;
        this.price = price;
        this.movieHall = movieHall;
    }
}
