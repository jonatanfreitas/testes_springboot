package com.financeiroteste.excenptionhandler;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class financeirotesteExcenptionHandler extends  ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,HttpHeaders headers, HttpStatus status, WebRequest request) {
		String messagemUsuario = messageSource.getMessage("mensagem.invalida", null,LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = Optional.ofNullable(ex.getCause()).orElse(ex).toString();    ;//ex.getCause() != null ?  ex.getCause().toString() : ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(messagemUsuario, mensagemDesenvolvedor));		
		return handleExceptionInternal(ex,erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Erro> erros = criarListaErro(ex.getBindingResult());	
		return handleExceptionInternal(ex, erros , headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({EntityNotFoundException.class})
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
		String messagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null,LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(messagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros,new HttpHeaders(),HttpStatus.NOT_FOUND,request);
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		String messagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null,LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);//ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(messagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros,new HttpHeaders(),HttpStatus.NOT_FOUND,request);
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,WebRequest request){
		String messagemUsuario = messageSource.getMessage("recurso.operacao-nao-permitida", null,LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
		List<Erro> erros = Arrays.asList(new Erro(messagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
	}
	
	private List<Erro> criarListaErro(BindingResult bindingResult){
		List<Erro> erros = new ArrayList<>();
		for (FieldError fieldError: bindingResult.getFieldErrors()) {
			String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = fieldError.toString();
			erros.add(new Erro(mensagemUsuario,mensagemDesenvolvedor));	
		}
		return erros;
	}
	
	
	
	public static class Erro {
		
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;
		
		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}

		
		public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}
		
	}
}
