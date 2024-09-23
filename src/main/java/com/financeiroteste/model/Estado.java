package com.financeiroteste.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ESTADOS")
public class Estado{

	@Id
	private String sigla;

	private String nome;
	
	@ManyToOne
	@JoinColumn(name="PAIS", referencedColumnName = "ISO3")
	private Pais pais;
	
	private String padraoRsdata;
	
}
