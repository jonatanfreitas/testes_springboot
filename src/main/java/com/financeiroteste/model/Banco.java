package com.financeiroteste.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "BANCOS")
public class Banco implements Serializable {

    @Id
    private Long id;

    private String nome;

    private String codCompensacao;

}
