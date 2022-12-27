package edu.school21.cinema.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "movie_hall", schema = Schema.SCHEMA)
public class MovieHall {

    @Id
    private String id;

    @Column(name = "number_of_seats", nullable = false)
    private Integer numberOfSeats;

    @OneToMany(mappedBy = "movieHall", cascade = CascadeType.ALL)
    private List<MovieSession> movieSessionList = new ArrayList<>();

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
