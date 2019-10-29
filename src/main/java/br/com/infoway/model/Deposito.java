package br.com.infoway.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;

import br.com.infoway.enums.TipoMovimentacao;

@Entity
public class Deposito extends Movimentacao {
	private static final long serialVersionUID = 1L;

	public Deposito() {
		super();
	}

	public Deposito(Long id, BigDecimal valor, Date data, Conta conta) {
		super(id, valor, data, conta, TipoMovimentacao.DEPOSITO);
	}
}