package br.com.infoway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infoway.model.Conta;

/**
 * 
 * @author Alexandre Lages
 * 
 * Implementação do repository de Conta
 */
public interface ContaRepository extends JpaRepository<Conta, Long>  {

	/**
	 * Retorna uma conta por numero
	 * @param numero
	 * @return conta
	 */
	Conta findByNumero(Integer numero);
}