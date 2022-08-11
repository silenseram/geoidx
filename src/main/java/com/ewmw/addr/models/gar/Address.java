package com.ewmw.addr.models.gar;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(schema = "public", name = "address")
public class Address {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    @Column(name = "OBJECTID")
    private Long objectId;

    @Getter @Setter
    @Column(name = "level")
    private int level;

    @Getter @Setter
    @Column(name = "addr")
    private String addr;

    @Getter @Setter
    @Column(name = "path")
    private String path;

    @Getter @Setter
    @Column(name = "uuid")
    private UUID uuid;
}
