package br.com.infoway.exception;

public class FormatoNumeroException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FormatoNumeroException(String mensagem) {
		super(mensagem);
	}
	
	public FormatoNumeroException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}