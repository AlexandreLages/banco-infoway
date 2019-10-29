package br.com.infoway.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(ObjetoJaExisteException.class)
	public ResponseEntity<TratamentoException> objetoJaExiste(ObjetoJaExisteException ex, HttpServletRequest request) {
		TratamentoException tratamentoException = new TratamentoException(HttpStatus.CONFLICT.value(), ex.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(tratamentoException);
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<TratamentoException> validaCampos(MethodArgumentNotValidException ex, HttpServletRequest request) {
		ValidacaoException validacaoException = new ValidacaoException(HttpStatus.BAD_REQUEST.value(), "Erros de validação", System.currentTimeMillis());
		for(FieldError error : ex.getBindingResult().getFieldErrors()) {
			validacaoException.adicionarErro(error.getField(), error.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validacaoException);
	}
	
	@ExceptionHandler(SaldoInsuficienteException.class)
	public ResponseEntity<TratamentoException> saldoInsuficiente(SaldoInsuficienteException ex, HttpServletRequest request) {
		TratamentoException tratamentoException = new TratamentoException(HttpStatus.FORBIDDEN.value(), ex.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tratamentoException);
	}

	@ExceptionHandler(SenhaIncorretaException.class)
	public ResponseEntity<TratamentoException> senhaIncorreta(SenhaIncorretaException ex, HttpServletRequest request) {
		TratamentoException tratamentoException = new TratamentoException(HttpStatus.FORBIDDEN.value(), ex.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tratamentoException);
	}
}