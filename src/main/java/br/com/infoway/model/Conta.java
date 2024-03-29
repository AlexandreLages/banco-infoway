package br.com.infoway.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.infoway.exception.SaldoInsuficienteException;

/**
 * 
 * @author Alexandre Lages
 * 
 * Implementação da classe modelo de Conta
 */

@Entity
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O valor não pode ser nulo!")
	private Integer numero;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	@Digits(integer = 10, fraction = 2, message = "O valor não está de acordo com o padrão!")
	private BigDecimal saldo;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(min = 6, max = 12, message = "Deve ter entre 6 e 12 caracteres")
	private String senha;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "agencia_id")
	private Agencia agencia;
	
	@JsonIgnore
	@OneToMany(mappedBy = "conta")
	private List<Movimentacao> movimentacoes = new ArrayList<>();
	
	public Conta() {
	}

	public Conta(Long id, Integer numero, BigDecimal saldo, String senha, Cliente cliente, Agencia agencia) {
		super();
		this.id = id;
		this.numero = numero;
		this.saldo = saldo;
		this.senha = senha;
		this.cliente = cliente;
		this.agencia = agencia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	
	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public BigDecimal debitar(BigDecimal valor) {
		if(valor.compareTo(saldo) == 1) {
			throw new SaldoInsuficienteException("A Conta " + numero + 
					" não possui saldo suficiente para realizar a transação!");
		}
		saldo = saldo.subtract(valor);
		return saldo;
	}
	
	public BigDecimal creditar(BigDecimal valor) {
		saldo = saldo.add(valor);
		return saldo;
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
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}