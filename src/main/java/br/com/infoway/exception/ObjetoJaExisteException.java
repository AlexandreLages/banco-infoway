package br.com.infoway.exception;

public class ObjetoJaExisteException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ObjetoJaExisteException(String mensagem) {
		super(mensagem);
	}
	
	public ObjetoJaExisteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}