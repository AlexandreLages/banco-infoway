package br.com.infoway.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoException extends TratamentoException {
	private static final long serialVersionUID = 1L;
	
	private List<ValidacaoError> erros = new ArrayList<>();
	
	public ValidacaoException(Integer status, String mensagem, Long timeStamp) {
		super(status, mensagem, timeStamp);
	}
	
	public List<ValidacaoError> getErros() {
		return erros;
	}
	
	public void adicionarErro(String campo, String mensagem) {
		erros.add(new ValidacaoError(campo, mensagem));
	}
}