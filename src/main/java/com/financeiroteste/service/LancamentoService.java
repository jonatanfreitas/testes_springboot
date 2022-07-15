package com.financeiroteste.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.financeiroteste.model.Lancamento;
import com.financeiroteste.model.Pessoa;
import com.financeiroteste.repository.LancamentoRepository;
import com.financeiroteste.repository.pessoaRepository;
import com.financeiroteste.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private pessoaRepository PessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = PessoaRepository.findById(lancamento.getPessoa().getCodigo())
				.orElseThrow(() -> new PessoaInexistenteOuInativaException());
		if (pessoa==null || pessoa.isInativo()) {
			throw new  PessoaInexistenteOuInativaException();
		}			
		return lancamentoRepository.save(lancamento);
	}

}
