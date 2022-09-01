package com.financeiroteste.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.financeiroteste.model.Pessoa;

public interface pessoaRepository extends JpaRepository<Pessoa, Long>{

	public Page<Pessoa> findByNomeContaining(String nome, Pageable pageable);
}
