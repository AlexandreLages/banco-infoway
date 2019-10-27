package br.com.infoway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infoway.model.Agencia;

/**
 * 
 * @author Alexandre Lages
 *
 * Implementação do repository de Agencia
 */

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long> {

	/**
	 * Pesquisa uma Agência por Nome
	 * @param nome
	 * @return agencia
	 */
	Agencia findByNome(String nome);
	
	/**
	 * Pesquisa uma Agência por CNPJ
	 * @param cnpj
	 * @return
	 */
	Agencia findByCnpj(String cnpj);
	
	/**
	 * Pesquisa uma Agência por Código
	 * @param codigo
	 * @return
	 */
	Agencia findByCodigo(Integer codigo);
}