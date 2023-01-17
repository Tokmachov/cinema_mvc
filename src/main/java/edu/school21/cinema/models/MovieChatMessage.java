package edu.school21.cinema.models;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "movie_chat_message", schema = Schema.SCHEMA)
@NoArgsConstructor
@Getter
@Setter
public class MovieChatMessage {
    @Id
    @GeneratedValue
    private long id;

    private String text;

    private long timeStamp;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_chat_message__user"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id", foreignKey = @ForeignKey(name = "fk_chat_message__movie"))
    private Movie movie;

    public MovieChatMessage(String text, long timeStamp, User user, Movie movie) {
        this.text = text;
        this.timeStamp = timeStamp;
        this.user = user;
        this.movie = movie;
    }
}
