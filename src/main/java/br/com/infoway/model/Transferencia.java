package br.com.infoway.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.infoway.enums.TipoMovimentacao;

@Entity
public class Transferencia extends Movimentacao {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Conta na qual o valor da transferência será creditado
	 */
	@ManyToOne
	@JoinColumn(name = "conta_creditada_id")
	private Conta contaDestino;

	public Transferencia() {
		super();
	}

	public Transferencia(Long id, BigDecimal valor, Date data, Conta conta, Conta contaDestino) {
		super(id, valor, data, conta, TipoMovimentacao.TRANSFERENCIA);
		this.contaDestino = contaDestino;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}
}