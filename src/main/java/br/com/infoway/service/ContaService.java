package br.com.infoway.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.exception.ObjetoJaExisteException;
import br.com.infoway.exception.ObjetoNaoEncontradoException;
import br.com.infoway.interfaces.ServiceInterface;
import br.com.infoway.model.Agencia;
import br.com.infoway.model.Cliente;
import br.com.infoway.model.Conta;
import br.com.infoway.repository.ContaRepository;

/**
 * 
 * @author Alexandre Lages
 * 
 * Implementação do service responsavel pela regra de negocio de Conta
 */
@Service
public class ContaService implements ServiceInterface<Conta>{

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private AgenciaService agenciaService;

	/**
	 * Método responsável por inserir uma Conta na base de dados
	 * @param conta
	 * @return conta
	 */
	@Override
	public Conta inserir(Conta conta) {
		if(validarInsercao(conta)) {
			Agencia agencia = agenciaService.pesquisarPorId(conta.getAgencia().getId());
			agencia.getContas().add(conta);
			
			Cliente cliente = clienteService.inserir(conta.getCliente());
			cliente.getContas().add(conta);
			
			conta.setAgencia(agencia);
			conta.setCliente(cliente);
			
			agenciaService.atualizar(agencia);
			clienteService.atualizar(conta.getCliente());
			
			return contaRepository.save(conta);
		}
		return null;
	}

	/**
	 * Método responsável por atualizar uma Conta na base de dados
	 * @param conta
	 * @return Conta
	 */
	@Override
	public Conta atualizar(Conta conta) {
		pesquisarPorId(conta.getId());
		return contaRepository.save(conta);
	}

	/**
	 * Método responsável por deletar uma Conta da base de dados
	 * @param id da conta a ser deletada
	 * @return void
	 */
	@Override
	public void deletar(Long id) {
		pesquisarPorId(id);
		contaRepository.deleteById(id);
	}
	
	/**
	 * Método responsável por pesquisar uma Conta por Id na base de dados
	 * @param id da conta pesquisada
	 * @return Conta
	 */
	@Override
	public Conta pesquisarPorId(Long id) {
		Optional<Conta> conta = contaRepository.findById(id);
		return conta.orElseThrow(() -> 
		new ObjetoNaoEncontradoException("Objeto não encontrado! Id: " + 
				id + ", Tipo: " + Conta.class.getName()));
	}

	/**
	 * Método responsável por listar todas as Contas da base de dados
	 * @param 
	 * @return List<Conta>
	 */
	@Override
	public List<Conta> listarTodos() {
		return contaRepository.findAll();
	}

	/**
	 * Metodo responsavel por validar a insercao de uma nova Conta na base de dados
	 * @param conta
	 * @return boolean
	 */
	private boolean validarInsercao(Conta conta) {
		if(contaRepository.findByNumero(conta.getNumero()) != null) {
			throw new ObjetoJaExisteException("Já existe uma Conta com o mesmo Número cadastrado!");
		}
		return true;
	}
	
	/**
	 * Metodo responsavel por pesquisar uma conta pelo seu numero
	 * @param numero da conta
	 * @return Conta
	 */
	public Conta pesquisarPorNumero(Integer numero) {
		Conta conta = contaRepository.findByNumero(numero);
		if(conta == null) {
			throw new ObjetoNaoEncontradoException("Conta " + numero + " inválida!");
		}
		return conta;
	}
}