package br.com.infoway.exception;

public class SaldoInsuficienteException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SaldoInsuficienteException(String mensagem) {
		super(mensagem);
	}
	
	public SaldoInsuficienteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}