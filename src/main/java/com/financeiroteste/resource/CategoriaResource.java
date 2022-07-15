package com.financeiroteste.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.financeiroteste.event.RecursoCriadoEvent;
import com.financeiroteste.event.listener.RecursoCriadoListener;
import com.financeiroteste.model.Pessoa;
import com.financeiroteste.model.categoria;
import com.financeiroteste.repository.categoriaRepository;
import com.financeiroteste.repository.pessoaRepository;


@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private categoriaRepository CategoriaRepository;
	
	
	@Autowired
	private ApplicationEventPublisher publisher; 
	
	//@CrossOrigin(maxAge=10, origins = {"http://localhost:8001"})
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<?> listar(){
		List<categoria> categorias = CategoriaRepository.findAll();
		return !categorias.isEmpty() ? ResponseEntity.ok(categorias) : ResponseEntity.noContent().build();
		
	}
	
	//@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and hasAuthority('SCOPE_write')")
	public ResponseEntity<categoria> criar(@Valid @RequestBody categoria Categoria1, HttpServletResponse response) {
		categoria categoriaSalva = CategoriaRepository.save(Categoria1);
		
		publisher.publishEvent(new RecursoCriadoEvent(this,response , categoriaSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and hasAuthority('SCOPE_read')")
	public ResponseEntity buscarPeloCodigo(@PathVariable Long codigo) {
		categoria categoriaBuscada = CategoriaRepository.findById(codigo).orElse(null);
		return categoriaBuscada != null ? ResponseEntity.ok(categoriaBuscada) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		CategoriaRepository.deleteById(codigo);
	}
	

	
	
	
	
	
	
}	