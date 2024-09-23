package com.financeiroteste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financeiroteste.model.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, String>{

	List<Pais> findByNomeContains(String nome);
	
}
