package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie", schema = "cinema")
public class Movie {
    @Id
    private String title;
    private int year;

    @Column(name = "age_restriction")
    private int ageRestriction;
    private String description;

    @Column(name = "poster_id")
    private String posterId;
}
