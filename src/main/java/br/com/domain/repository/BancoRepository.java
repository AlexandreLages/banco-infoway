package br.com.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.model.Banco;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {

}