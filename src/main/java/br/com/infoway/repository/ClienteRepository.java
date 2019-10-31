package br.com.infoway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infoway.model.Cliente;

/**
 * 
 * @author Alexandre Lages
 *
 * Implementação do repository de Cliente
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long>  {

	/**
	 * Pesquisa um cliente por cpfOuCnpj
	 * @param cpfOuCnpj
	 * @return cliente
	 */
	Cliente findByCpfOuCnpj(String cpfOuCnpj);
}