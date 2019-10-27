package br.com.infoway.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Alexandre Lages
 *
 * Implementação da classe de modelo Banco
 */

@Entity
public class Banco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Preenchimento obrigatório!")
	private Integer codigo;
	
	@Size(min = 6, max = 50, message = "Deve conter entre 6 e 50 caracteres!")
	@NotEmpty(message = "Preenchimento obrigatório!")
	private String nome;
	
	@Size(min = 14, max = 14, message = "Deve conter exatamente 14 caracteres!")
	@NotEmpty(message = "Preenchimento obrigatório!")
	private String cnpj;
	
	@JsonIgnore
	@OneToMany(mappedBy = "banco")
	private List<Agencia> agencias = new ArrayList<>();
	
	public Banco() {
	}

	/**
	 * 
	 * @param id
	 * @param codigo
	 * @param nome
	 * @param cnpj
	 */
	public Banco(Long id, @NotNull Integer codigo, @NotNull String nome, @NotNull String cnpj) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.cnpj = cnpj;
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

	public List<Agencia> getAgencias() {
		return agencias;
	}

	public void setAgencias(List<Agencia> agencias) {
		this.agencias = agencias;
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
		Banco other = (Banco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}