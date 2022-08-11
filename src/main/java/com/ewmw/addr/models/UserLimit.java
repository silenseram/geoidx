package com.ewmw.addr.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(schema = "public", name = "user_limit")
public class UserLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;

//    @Column(name = "user_id")
//    @Getter @Setter
//    @NotBlank
//    private int userId;

    @Column(name = "limit_id")
    @Getter @Setter
    private int limitId;

    @Column(name = "queries_left")
    @Getter @Setter
    private int queriesLeft;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter @Setter
    private User user;
}
