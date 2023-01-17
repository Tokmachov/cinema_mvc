package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_authentication", schema = Schema.SCHEMA)
@NoArgsConstructor
@Getter
@Setter
public class UserAuthentication {
    @Id
    @GeneratedValue
    private long id;

    private String remoteAddress;
    private long timeStamp;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    public UserAuthentication(String remoteAddress, long timeStamp, User user) {
        this.remoteAddress = remoteAddress;
        this.timeStamp = timeStamp;
        this.user = user;
    }
}
