package br.com.infoway.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.enums.TipoMovimentacao;
import br.com.infoway.exception.SenhaIncorretaException;
import br.com.infoway.interfaces.ServiceInterface;
import br.com.infoway.model.Conta;
import br.com.infoway.model.Saque;
import br.com.infoway.repository.MovimentacaoRepository;

/**
 * 
 * @author Alexandre Lages
 * 
 * Implementação do service de Movimentação
 */
@Service
public class SaqueService implements ServiceInterface<Saque>{

	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	private ContaService contaService;

	@Override
	public Saque inserir(Saque t) {
		Conta conta = contaService.pesquisarPorNumero(t.getConta().getNumero());
		
		if(conta.getSenha().equals(t.getConta().getSenha()) == false) {
			throw new SenhaIncorretaException("A senha para a conta " + conta.getNumero() + 
					" está incorreta!");
		}
		
		conta.saque(t.getValor());
		conta.getMovimentacoes().add(t);
		
		t.setTipo(TipoMovimentacao.SAQUE);
		t.setConta(conta);
		t.setData(new Date());
		
		contaService.atualizar(conta);
		return movimentacaoRepository.save(t);
	}

	@Override
	public Saque atualizar(Saque t) {
		return null;
	}

	@Override
	public void deletar(Long id) {
	}

	@Override
	public Saque pesquisarPorId(Long id) {
		return null;
	}

	@Override
	public List<Saque> listarTodos() {
		return null;
	}

	
}