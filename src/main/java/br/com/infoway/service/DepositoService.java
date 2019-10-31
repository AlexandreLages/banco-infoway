package br.com.infoway.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.enums.TipoMovimentacao;
import br.com.infoway.interfaces.ServiceInterface;
import br.com.infoway.model.Conta;
import br.com.infoway.model.Deposito;
import br.com.infoway.repository.MovimentacaoRepository;

/**
 * 
 * @author Alexandre Lages
 * 
 * Implementação do service responsavel pela regra de negocio de Movimentação do tipo Deposito
 */
@Service
public class DepositoService implements ServiceInterface<Deposito>{

	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	private ContaService contaService;

	@Override
	/**
	 * Método responsável por inserir um deposito na base de dados
	 * @param deposito
	 * @return deposito
	 */
	public Deposito inserir(Deposito deposito) {
		Conta conta = contaService.pesquisarPorNumero(deposito.getConta().getNumero());
		conta.creditar(deposito.getValor());
		conta.getMovimentacoes().add(deposito);
		
		deposito.setTipo(TipoMovimentacao.DEPOSITO);
		deposito.setConta(conta);
		deposito.setData(new Date());
		
		contaService.atualizar(conta);
		return movimentacaoRepository.save(deposito);
	}

	@Override
	public Deposito atualizar(Deposito t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Deposito pesquisarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Deposito> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
}