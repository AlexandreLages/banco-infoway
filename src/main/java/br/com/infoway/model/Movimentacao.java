package br.com.infoway.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.infoway.enums.TipoMovimentacao;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Movimentacao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	@NotNull(message = "Preenchimento obrigatório!")
	@Digits(integer = 10, fraction = 2, message = "O valor não está de acordo com o padrão!")
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name = "conta_principal_id")
	private Conta conta;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data;
	
	private Integer tipo;
	
	public Movimentacao() {
	}

	public Movimentacao(Long id, BigDecimal valor, Date data, Conta conta, TipoMovimentacao tipoMovimentacao) {
		super();
		this.id = id;
		this.valor = valor;
		this.data = data;
		this.conta = conta;
		this.tipo = tipoMovimentacao.getCodigo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public TipoMovimentacao getTipo() {
		return TipoMovimentacao.toEnum(tipo);
	}

	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo.getCodigo();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimentacao other = (Movimentacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}