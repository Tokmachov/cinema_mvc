package edu.school21.cinema.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_picture", schema = Schema.SCHEMA)
@NoArgsConstructor
@Getter
@Setter
public class UserPicture {

    @Id
    private String id;

    private String originalName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true, foreignKey = @ForeignKey(name = "fk_picture__user"))
    private User user;

    public UserPicture(String id, String originalName, User user) {
        this.id = id;
        this.originalName = originalName;
        this.user = user;
    }
}
