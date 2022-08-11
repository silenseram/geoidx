package com.ewmw.addr.models.gar;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gar_house", schema = "public")
public class GarHouse {
    @Id
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;

    @Getter @Setter
    @Column(name = "OBJECTGUID")
    private String objectGuid;

    @Getter @Setter
    @Column(name = "OBJECTID")
    private Long objectId;

    @Getter @Setter
    @Column(name = "HOUSENUM")
    private String houseNum;

    @Getter @Setter
    @Column(name = "HOUSETYPE")
    private Long houseType;

    @Getter @Setter
    @Column(name = "ISACTUAL")
    private int isActual;

    @Getter @Setter
    @Column(name = "ISACTIVE")
    private int isActive;

    @Getter @Setter
    @Column(name = "ADDNUM1")
    private String addNum1;

    @Getter @Setter
    @Column(name = "ADDNUM2")
    private String addNum2;

    @Getter @Setter
    @Column(name = "ADDTYPE1")
    private String addType1;

    @Getter @Setter
    @Column(name = "ADDTYPE2")
    private String addType2;

    @Getter @Setter
    @Column(name = "kadnum")
    private String kadnum;

    @Getter @Setter
    @Column(name = "ifns_fl")
    private String ifnsFl;

    @Getter @Setter
    @Column(name = "ifns_ul")
    private String ifnsUl;

    @Getter @Setter
    @Column(name = "ter_ifns_fl")
    private String terIfnsFl;

    @Getter @Setter
    @Column(name = "ter_ifns_ul")
    private String terIfnsUl;

    @Getter @Setter
    @Column(name = "postalcode")
    private String postalCode;

    @Getter @Setter
    @Column(name = "division_type")
    private String divisionType;

    @Getter @Setter
    @Column(name = "okato")
    private String okato;

    @Getter @Setter
    @Column(name = "oktmo")
    private String oktmo;

    @Getter @Setter
    @Column(name = "kladr_code")
    private String kladrCode;

    @Getter @Setter
    @Column(name = "region_code")
    private String regionCode;

    @Getter @Setter
    @Column(name = "reestr_num")
    private String reestrNum;

    @Getter @Setter
    @Column(name = "official_name")
    private String officialName;

    @Getter @Setter
    @Column(name = "post_status")
    private Boolean postStatus;


}
