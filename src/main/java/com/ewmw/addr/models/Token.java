package com.ewmw.addr.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "token")
@Table(schema = "public", name = "token")
public class Token {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column(name = "value", nullable = false)
    @Getter @Setter
    private String value;

    @Column(name = "user_id")
    @Getter @Setter
    private Long userId;

    @Column(name = "limit", nullable = false)
    @Getter @Setter
    private Long limit;

    @Column(name = "spent", nullable = false)
    @Getter @Setter
    private Long spent;

    @Column(name = "created_at", nullable = false)
    @Getter @Setter
    private Long createdAt;


}
