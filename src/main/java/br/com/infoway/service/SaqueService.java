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
 * Implementação do service responsavel pela regra de negocios de Movimentação do tipo Saque
 */
@Service
public class SaqueService implements ServiceInterface<Saque>{

	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	private ContaService contaService;

	@Override
	/**
	 * Método responsável por inserir um saque na base de dados
	 * @param saque
	 * @return saque
	 */
	public Saque inserir(Saque saque) {
		Conta conta = contaService.pesquisarPorNumero(saque.getConta().getNumero());
		
		if(conta.getSenha().equals(saque.getConta().getSenha()) == false) {
			throw new SenhaIncorretaException("A senha para a conta " + conta.getNumero() + 
					" está incorreta!");
		}
		
		conta.debitar(saque.getValor());
		conta.getMovimentacoes().add(saque);
		
		saque.setTipo(TipoMovimentacao.SAQUE);
		saque.setConta(conta);
		saque.setData(new Date());
		
		contaService.atualizar(conta);
		return movimentacaoRepository.save(saque);
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