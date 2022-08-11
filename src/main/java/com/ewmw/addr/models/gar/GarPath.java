package com.ewmw.addr.models.gar;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(schema = "public")
public class GarPath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter @Setter
    private Long id;

    @Column
    @Getter @Setter
    private String path;

    @Column(name = "is_active")
    @Getter @Setter
    private String isActive;

    @Column(name = "OBJECTID")
    @Getter @Setter
    private Long objetId;

//    @Column("uuid")
//    @Getter @Setter
//    private UUID objectGuid;
}
