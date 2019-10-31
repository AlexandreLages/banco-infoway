package br.com.infoway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infoway.model.Banco;

/**
 * 
 * @author Alexandre Lages
 *
 * Implementação do repository de Banco
 */

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {

	/**
	 * Pesquisar Banco por código
	 * @param codigo
	 * @return banco
	 */
	Banco findByCodigo(Integer codigo);
	
	/**
	 * Pesquisar Banco por nome
	 * @param nome
	 * @return banco
	 */
	Banco findByNome(String nome);
	
	/**
	 * Pesquisar Banco por CNPJ
	 * @param cnpj
	 * @return banco
	 */
	Banco findByCnpj(String cnpj);
}