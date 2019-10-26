package br.com.infoway.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Alexandre Lages
 *
 * Implementação da classe de modelo Agência
 */

@Entity
public class Agencia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Preenchimento obrigatório!")
	private Integer codigo;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(min = 6, max = 50, message = "Deve conter entre 6 e 50 caracteres!")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(min = 14, max = 14, message = "Deve conter exatamente 14 caracteres!")
	private String cnpj;
	
	@ManyToOne
	@JoinColumn(name = "banco_id")
	private Banco banco;
	
	public Agencia() {
	}

	public Agencia(Long id, @NotNull Integer codigo, @NotNull String nome, @NotNull String cnpj, Banco banco) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.cnpj = cnpj;
		this.banco = banco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
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
		Agencia other = (Agencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}