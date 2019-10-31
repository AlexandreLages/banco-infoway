package br.com.infoway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infoway.model.Conta;
import br.com.infoway.model.Movimentacao;

/**
 * 
 * @author Alexandre Lages
 * 
 * Implementação do repository de Movimentacao
 */
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>  {

	/**
	 * Lista as movimentações de uma conta
	 * @param conta
	 * @return List<Movimentacao>
	 */
	List<Movimentacao> findByConta(Conta conta);
}