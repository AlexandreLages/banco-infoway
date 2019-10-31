package br.com.infoway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infoway.model.Conta;
import br.com.infoway.model.Transferencia;

/**
 * 
 * @author Alexandre Lages
 * 
 * Implementação do repository de Movimentacao
 */
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>  {

	/**
	 * Lista as trasnferências recebidas por uma conta
	 * @param conta
	 * @return List<Transferencia>
	 */
	List<Transferencia> findByContaDestino(Conta conta);
}