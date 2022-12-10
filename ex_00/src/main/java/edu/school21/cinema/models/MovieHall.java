package edu.school21.cinema.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "movie_hall", schema = "cinema")
public class MovieHall {

    @Id
    private String id;

    @Column(name = "number_of_seats", nullable = false)
    private Integer numberOfSeats;

    public MovieHall() {}

    public MovieHall(String id, int numberOfSeats) {
        this.id = id;
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "MovieHall{" +
                "id='" + id + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
}
