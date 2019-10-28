package br.com.infoway.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Saque extends Movimentacao {
	private static final long serialVersionUID = 1L;

	public Saque() {
		super();
	}

	public Saque(Long id, BigDecimal valor, Conta conta, Date data) {
		super(id, valor, conta, data);
	}
}