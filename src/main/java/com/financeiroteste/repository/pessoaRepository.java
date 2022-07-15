package com.financeiroteste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financeiroteste.model.Pessoa;

public interface pessoaRepository extends JpaRepository<Pessoa, Long>{

}
