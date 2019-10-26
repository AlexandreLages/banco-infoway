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
import br.com.infoway.repository.BancoRepository;

@Service
public class AgenciaService implements ServiceInterface<Agencia> {

	@Autowired
	private AgenciaRepository agenciaRepository;
	
	@Autowired
	private BancoRepository bancoRepository;

	@Override
	public Agencia inserir(Agencia agencia) {
		if (validarInsercao(agencia)) {
			Optional<Banco> banco = bancoRepository.findById(agencia.getBanco().getId());
			banco.get().getAgencias().add(agencia);
			agencia.setBanco(banco.get());
			bancoRepository.save(banco.get());
			return agenciaRepository.save(agencia);
		}
		return null;
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