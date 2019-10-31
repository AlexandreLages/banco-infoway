package br.com.infoway.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.exception.ObjetoJaExisteException;
import br.com.infoway.exception.ObjetoNaoEncontradoException;
import br.com.infoway.interfaces.ServiceInterface;
import br.com.infoway.model.Agencia;
import br.com.infoway.model.Banco;
import br.com.infoway.repository.AgenciaRepository;

/**
 * 
 * @author Alexandre Lages
 *
 * Implementação do service responsavel pela regra de negócio de Agência
 */

@Service
public class AgenciaService implements ServiceInterface<Agencia> {

	@Autowired
	private AgenciaRepository agenciaRepository;
	
	@Autowired
	private BancoService bancoService;

	/**
	 * Método service responsável por inserir uma Agência na base de dados
	 * @param agencia
	 * @return agencia
	 */
	@Override
	public Agencia inserir(Agencia agencia) {
		if (validarInsercao(agencia)) {
			Banco banco = bancoService.pesquisarPorId(agencia.getBanco().getId());
			banco.getAgencias().add(agencia);
			agencia.setBanco(banco);
			bancoService.atualizar(banco);
			return agenciaRepository.save(agencia);
		}
		return null;
	}

	/**
	 * Método service responsável por atualizar uma Agência na base de dados
	 * @param agencia
	 * @return Agencia
	 */
	@Override
	public Agencia atualizar(Agencia agencia) {
		pesquisarPorId(agencia.getId());
		return agenciaRepository.save(agencia);
	}

	/**
	 * Método service responsável por deletar uma Agência da base de dados
	 * @param id da agência a ser deletada
	 * @return void
	 */
	@Override
	public void deletar(Long id) {
		pesquisarPorId(id);
		agenciaRepository.deleteById(id);
	}

	/**
	 * Método service responsável por pesquisar uma Agência na base de dados
	 * @param id da agência pesquisada
	 * @return agencia
	 */
	@Override
	public Agencia pesquisarPorId(Long id) {
		Optional<Agencia> agencia = agenciaRepository.findById(id);
		return agencia.orElseThrow(() -> 
		new ObjetoNaoEncontradoException("Agência inválida!"));
	}

	/**
	 * Método service responsável por listar todas as Agências da base de dados
	 * @param
	 * @return List<Agencia>
	 */
	@Override
	public List<Agencia> listarTodos() {
		return agenciaRepository.findAll();
	}
	
	/**
	 * Método responsável por validar a inserção de uma nova agência na base de daos
	 * Valida por nome, código e cnpj
	 * @param agencia
	 * @return boolean 
	 */
	private boolean validarInsercao(Agencia agencia) {
		if(agenciaRepository.findByNome(agencia.getNome()) != null) {
			throw new ObjetoJaExisteException("Já existe uma Agência com o mesmo Nome cadastrado!");
		}
		if(agenciaRepository.findByCodigo(agencia.getCodigo()) != null) {
			throw new ObjetoJaExisteException("Já existe uma Agência com o mesmo Código cadastrado!");
		}
		if(agenciaRepository.findByCnpj(agencia.getCnpj()) != null) {
			throw new ObjetoJaExisteException("Já existe uma Agência com o mesmo CNPJ cadastrado!");
		}
		return true;
	}
}