package com.financeiroteste.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PAISES")
public class Pais  {

    @Id
    private String iso3;

    private String ddi;

    @Column(name = "CODIGO_ESOCIAL")
    private String codigoEsocial;

    private String nome;

    public Pais(String iso3) {
        this.iso3 = iso3;
    }


}
