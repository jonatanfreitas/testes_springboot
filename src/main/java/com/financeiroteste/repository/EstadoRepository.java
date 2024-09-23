package com.financeiroteste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financeiroteste.model.Estado;


@Repository
public interface EstadoRepository extends JpaRepository<Estado, String> {

	
}
