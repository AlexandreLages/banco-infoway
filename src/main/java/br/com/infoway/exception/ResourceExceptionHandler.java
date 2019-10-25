package br.com.infoway.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<TratamentoException> objectNotFound(ObjetoNaoEncontradoException ex, HttpServletRequest request) {
		TratamentoException tratamentoException = new TratamentoException(HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tratamentoException);
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<TratamentoException> formatoNumero(NumberFormatException ex, HttpServletRequest request) {
		TratamentoException tratamentoException = new TratamentoException(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tratamentoException);
	}
	
	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<TratamentoException> formatoJson(JsonParseException ex, HttpServletRequest request) {
		TratamentoException tratamentoException = new TratamentoException(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tratamentoException);
	}
}