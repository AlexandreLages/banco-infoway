package br.com.infoway.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.exception.ObjetoNaoEncontradoException;
import br.com.infoway.interfaces.ServiceInterface;
import br.com.infoway.model.Cliente;
import br.com.infoway.repository.ClienteRepository;

/**
 * 
 * @author Alexandre Lages
 * 
 * Implementação do service responsavel pela regra de negócio de Cliente
 */
@Service
public class ClienteService implements ServiceInterface<Cliente>{

	@Autowired
	private ClienteRepository clienteRepository;

	/**
	 * Método responsável por inserir um Cliente na base de dados
	 * @param cliente
	 * @return cliente
	 */
	@Override
	public Cliente inserir(Cliente cliente) {
		Cliente clientePesquisado = clienteRepository.findByCpfOuCnpj(cliente.getCpfOuCnpj());
		if(clientePesquisado == null) {
			return clienteRepository.save(cliente);
		}
		return clienteRepository.save(clientePesquisado);
	}

	/**
	 * Método responsável por atualizar um Cliente na base de dados
	 * @param cliente
	 * @return cliente
	 */
	@Override
	public Cliente atualizar(Cliente cliente) {
		pesquisarPorId(cliente.getId());		
		return clienteRepository.save(cliente);
	}

	/**
	 * Método responsável por deletar um Cliente da base de dados
	 * @param id do cliente a ser deletado
	 * @return void
	 */
	@Override
	public void deletar(Long id) {
		pesquisarPorId(id);
		clienteRepository.deleteById(id);
	}

	/**
	 * Método responsável por pesquisar um Cliente por Id na base de dados
	 * @param id do cliente pesquisado 
	 * @return cliente
	 */
	@Override
	public Cliente pesquisarPorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> 
		new ObjetoNaoEncontradoException("Objeto não encontrado! Id: " + 
				id + ", Tipo: " + Cliente.class.getName()));
	}

	/**
	 * Método responsável por listar todos os Clientes da base de dados
	 * @param 
	 * @return List<Cliente>
	 */
	@Override
	public List<Cliente> listarTodos() {
		return clienteRepository.findAll();
	}
}