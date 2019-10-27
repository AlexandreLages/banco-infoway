package br.com.infoway.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.infoway.enums.TipoPessoa;

/**
 * 
 * @author Alexandre Lages
 *
 * Implementação da classe modelo de Cliente
 */

@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(min = 6, max = 50, message = "Deve conter entre 6 e 50 caracteres!")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(min = 11, max = 14, message = "Deve conter entre 11 e 14 caracteres!")
	private String cpfOuCnpj;
	
	@NotNull(message = "Valor não pode ser nulo")
	private Integer tipoPessoa;
	
	private List<Conta> contas = new ArrayList<>();
	
	public Cliente() {
	}

	/**
	 * 
	 * @param id
	 * @param nome
	 * @param cpfOuCnpj
	 * @param tipoPessoa
	 */
	public Cliente(Long id, String nome, String cpfOuCnpj, TipoPessoa tipoPessoa) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipoPessoa = tipoPessoa.getCodigo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(Integer tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}