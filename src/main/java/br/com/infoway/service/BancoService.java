package br.com.infoway.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.exception.ObjetoJaExisteException;
import br.com.infoway.exception.ObjetoNaoEncontradoException;
import br.com.infoway.interfaces.ServiceInterface;
import br.com.infoway.model.Banco;
import br.com.infoway.repository.BancoRepository;

/**
 * 
 * @author Alexandre Lages
 *
 * Implementação do service responsável pela regra de negócio de Banco
 */

@Service
public class BancoService implements ServiceInterface<Banco> {

	@Autowired
	private BancoRepository bancoRepository;

	/**
	 * Método service responsável por inserir um Banco na base de dados
	 * @param banco
	 * @return banco
	 */
	@Override
	public Banco inserir(Banco banco) {
		if (validarInsercao(banco)) {
			banco.setId(null);
			return bancoRepository.save(banco);
		}
		return null;
	}

	/**
	 * Método service responsável por atualizar um Banco na base de dados
	 * @param banco
	 * @return banco
	 */
	@Override
	public Banco atualizar(Banco banco) {
		pesquisarPorId(banco.getId());
		return bancoRepository.save(banco);
	}

	/**
	 * Método service responsável por deletar um Banco da base de dados
	 * @param banco
	 * @return void
	 */
	@Override
	public void deletar(Long id) {		
		pesquisarPorId(id);
		bancoRepository.deleteById(id);
	}

	/**
	 * Método service responsável por pesquisar um Banco na base de dados
	 * @param banco
	 * @return banco
	 */
	@Override
	public Banco pesquisarPorId(Long id) {
		Optional<Banco> banco = bancoRepository.findById(id);
		return banco.orElseThrow(() -> 
		new ObjetoNaoEncontradoException("Objeto não encontrado! Id: " + 
				id + ", Tipo: " + Banco.class.getName()));
	}

	/**
	 * Método service responsável por listar todos os Bancos da base de dados
	 * @param 
	 * @return List<Banco>
	 */
	@Override
	public List<Banco> listarTodos() {
		return bancoRepository.findAll();
	}
	
	/**
	 * Metodo que pesquisa um banco pelo seu código
	 * @param codigo
	 * @return
	 */
	public Banco pesquisarPorCodigo(Integer codigo) {
		return bancoRepository.findByCodigo(codigo);
	}
	
	/**
	 * Metodo responsavel por validar a insercao de um novo banco na base de dados
	 * Valida por nome, codigo e cnpj
	 * @param banco
	 * @return
	 */
	private boolean validarInsercao(Banco banco) {
		if(bancoRepository.findByNome(banco.getNome()) != null) {
			throw new ObjetoJaExisteException("Já existe um Banco com o mesmo Nome cadastrado!");
		}
		if(bancoRepository.findByCodigo(banco.getCodigo()) != null) {
			throw new ObjetoJaExisteException("Já existe um Banco com o mesmo Código cadastrado!");
		}
		if(bancoRepository.findByCnpj(banco.getCnpj()) != null) {
			throw new ObjetoJaExisteException("Já existe um Banco com o mesmo Cnpj cadastrado!");
		}
		return true;
	}
}