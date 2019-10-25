package br.com.infoway.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.exception.ObjetoNaoEncontradoException;
import br.com.infoway.interfaces.ServiceInterface;
import br.com.infoway.model.Banco;
import br.com.infoway.repository.BancoRepository;

@Service
public class BancoService implements ServiceInterface<Banco> {

	@Autowired
	private BancoRepository bancoRepository;

	@Override
	public Banco inserir(Banco object) {
		object.setId(null);
		return bancoRepository.save(object);
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
}