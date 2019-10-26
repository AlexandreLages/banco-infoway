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

@Service
public class BancoService implements ServiceInterface<Banco> {

	@Autowired
	private BancoRepository bancoRepository;

	@Override
	public Banco inserir(Banco banco) {
		if (validarInsercao(banco)) {
			banco.setId(null);
			return bancoRepository.save(banco);
		}
		return null;
	}

	@Override
	public Banco atualizar(Banco banco) {
		pesquisarPorId(banco.getId());
		return bancoRepository.save(banco);
	}

	@Override
	public void deletar(Long id) {		
		pesquisarPorId(id);
		bancoRepository.deleteById(id);
	}

	@Override
	public Banco pesquisarPorId(Long id) {
		Optional<Banco> banco = bancoRepository.findById(id);
		return banco.orElseThrow(() -> 
		new ObjetoNaoEncontradoException("Objeto não encontrado! Id: " + 
				id + ", Tipo: " + Banco.class.getName()));
	}

	@Override
	public List<Banco> listarTodos() {
		return bancoRepository.findAll();
	}
	
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