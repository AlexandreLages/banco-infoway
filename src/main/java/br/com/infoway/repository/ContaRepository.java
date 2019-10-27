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

}