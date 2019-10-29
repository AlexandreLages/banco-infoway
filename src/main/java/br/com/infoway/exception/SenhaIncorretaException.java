package br.com.infoway.exception;

public class SenhaIncorretaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SenhaIncorretaException(String mensagem) {
		super(mensagem);
	}
	
	public SenhaIncorretaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}