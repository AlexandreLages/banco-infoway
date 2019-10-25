package br.com.infoway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infoway.model.Agencia;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long> {
	
}