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
@Table(schema = Schema.SCHEMA)
@NamedEntityGraph(name = "user-userPicture-graph", attributeNodes = {
        @NamedAttributeNode("userPictureList")
})
public class User {
    @Id
    private String name;

    @OneToMany(mappedBy = "user")
    private List<UserPicture> userPictureList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "current_picture_id")
    private UserPicture currentUserPicture;

    public User(String name) {
        this.name = name;
    }
}
