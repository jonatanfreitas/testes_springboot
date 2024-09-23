package com.financeiroteste.security;

import java.util.Collection;

import com.financeiroteste.model.Usuario;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Slf4j
public class UsuarioSistema extends User {
    private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		log.info("Log UsuarioSistema loadUserByUsername: "+usuario.getEmail()+"  "+ usuario.getSenha());
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
