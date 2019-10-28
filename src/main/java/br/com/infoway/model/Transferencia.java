package br.com.infoway.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Transferencia extends Movimentacao {
	private static final long serialVersionUID = 1L;
	
	private Conta contaDestino;

	public Transferencia() {
		super();
	}

	public Transferencia(Long id, BigDecimal valor, Conta conta, Date data, Conta contaDestino) {
		super(id, valor, conta, data);
		this.contaDestino = contaDestino;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}
}