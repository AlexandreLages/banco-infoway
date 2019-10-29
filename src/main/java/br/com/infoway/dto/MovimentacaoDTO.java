package br.com.infoway.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.infoway.enums.TipoMovimentacao;

public class MovimentacaoDTO {

	@Digits(integer = 10, fraction = 2)
	private BigDecimal valor;
	
	private Integer numeroContaDebitada;
	private Integer numeroContaCreditada;
	private Integer tipo;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data;
	
	public MovimentacaoDTO(BigDecimal valor, Integer numeroContaDebitada, Integer numeroContaCreditada, Integer tipo, Date data) {
		super();
		this.valor = valor;
		this.numeroContaDebitada = numeroContaDebitada;
		this.numeroContaCreditada = numeroContaCreditada;
		this.tipo = tipo;
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getNumeroContaDebitada() {
		return numeroContaDebitada;
	}

	public void setNumeroContaDebitada(Integer numeroContaDebitada) {
		this.numeroContaDebitada = numeroContaDebitada;
	}

	public Integer getNumeroContaCreditada() {
		return numeroContaCreditada;
	}

	public void setNumeroContaCreditada(Integer numeroContaCreditada) {
		this.numeroContaCreditada = numeroContaCreditada;
	}

	public TipoMovimentacao getTipo() {
		return TipoMovimentacao.toEnum(tipo);
	}

	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo.getCodigo();
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}