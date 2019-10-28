package br.com.infoway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infoway.model.Movimentacao;

/**
 * 
 * @author Alexandre Lages
 * 
 * Implementação do repository de Movimentacao
 */
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>  {

}