package com.ewmw.addr.models.gar;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gar_addr", schema = "public")
public class GarAddr {

    @Id
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;

    @Getter @Setter
    @Column(name = "level")
    private int level;

    @Getter @Setter
    @Column(name = "OBJECTGUID")
    private String objectGuid;

    @Getter @Setter
    @Column(name = "OBJECTID")
    private Long objectId;

    @Getter @Setter
    @Column(name = "NAME")
    private String name;

    @Getter @Setter
    @Column(name = "TYPENAME")
    private String typeName;

    @Getter @Setter
    @Column(name = "PREVID")
    private String prevId;

    @Getter @Setter
    @Column(name = "NEXTID")
    private String nextId;

    @Getter @Setter
    @Column(name = "CHANGEID")
    private String changeId;

    @Getter @Setter
    @Column(name = "OPERTYPEID")
    private String operTypeId;

    @Getter @Setter
    @Column(name = "STARTDATE")
    private String startDate;

    @Getter @Setter
    @Column(name = "ENDDATE")
    private String endDate;

    @Getter @Setter
    @Column(name = "UPDATEDATE")
    private String updateDate;

    @Getter @Setter
    @Column(name = "OKTMO")
    private Long OKTMO;

    @Getter @Setter
    @Column(name = "OKATO")
    private Long OKATO;


}
