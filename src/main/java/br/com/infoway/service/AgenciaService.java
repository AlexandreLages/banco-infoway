package br.com.infoway.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.exception.ObjetoNaoEncontradoException;
import br.com.infoway.interfaces.ServiceInterface;
import br.com.infoway.model.Agencia;
import br.com.infoway.repository.AgenciaRepository;

@Service
public class AgenciaService implements ServiceInterface<Agencia> {

	@Autowired
	private AgenciaRepository agenciaRepository;

	@Override
	public Agencia inserir(Agencia t) {
		t.setId(null);
		return agenciaRepository.save(t);
	}

	@Override
	public Agencia atualizar(Agencia t) {
		pesquisarPorId(t.getId());
		return agenciaRepository.save(t);
	}

	@Override
	public void deletar(Long id) {
		pesquisarPorId(id);
		agenciaRepository.deleteById(id);
	}

	@Override
	public Agencia pesquisarPorId(Long id) {
		Optional<Agencia> agencia = agenciaRepository.findById(id);
		return agencia.orElseThrow(() -> 
		new ObjetoNaoEncontradoException("Agência inválida!"));
	}

	@Override
	public List<Agencia> listarTodos() {
		return agenciaRepository.findAll();
	}
}