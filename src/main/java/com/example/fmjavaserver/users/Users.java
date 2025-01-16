package com.example.fmjavaserver.users;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table

@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filters_sequence")
    @SequenceGenerator(name = "filters_sequence", sequenceName = "filters_sequence", allocationSize = 1)

    private Long _id;
    private String username;
    private String password;
    private String email;
    private String admin;

    public Users() {

    }

    public Users(Long _id, String username, String password, String email,
            String admin) {
        this._id = _id;
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.email = email;
    }

    public Users(String username, String password, String email,
            String admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.email = email;
    }
}
