package br.com.infoway.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.infoway.interfaces.ServiceInterface;
import br.com.infoway.model.Agencia;

/**
 * 
 * @author Alexandre Lages
 * 
 * Implementação do service de Conta
 */
@Service
public class ContaService implements ServiceInterface<Agencia> {

	@Override
	public Agencia inserir(Agencia t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Agencia atualizar(Agencia t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Agencia pesquisarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Agencia> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}	
}