package br.com.infoway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infoway.model.Banco;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {

	Banco findByCodigo(Integer codigo);
	
	Banco findByNome(String nome);
	
	Banco findByCnpj(String cnpj);
}