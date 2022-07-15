package com.financeiroteste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financeiroteste.model.Lancamento;
import com.financeiroteste.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery{
	
}
