package br.com.infoway.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.enums.TipoMovimentacao;
import br.com.infoway.exception.SenhaIncorretaException;
import br.com.infoway.interfaces.ServiceInterface;
import br.com.infoway.model.Conta;
import br.com.infoway.model.Transferencia;
import br.com.infoway.repository.MovimentacaoRepository;

/**
 * 
 * @author Alexandre Lages
 * 
 * Implementação do service responsavel pela regra de negocios de Movimentação do tipo
 * transferencia
 */
@Service
public class TransferenciaService implements ServiceInterface<Transferencia>{

	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	private ContaService contaService;

	@Override
	/**
	 * Método responsável por inserir uma transferencia na base de dados
	 * @param transferencia
	 * @return transferencia
	 */
	public Transferencia inserir(Transferencia transferencia) {
		Conta contaDebitada = contaService.pesquisarPorNumero(transferencia.getConta().getNumero());
		
		if(contaDebitada.getSenha().equals(transferencia.getConta().getSenha()) == false) {
			throw new SenhaIncorretaException("A senha para a conta " + contaDebitada.getNumero() + 
					" está incorreta!");
		}
		
		contaDebitada.debitar(transferencia.getValor());
		contaDebitada.getMovimentacoes().add(transferencia);
		
		Conta contaCreditada = contaService.pesquisarPorNumero(transferencia.getContaDestino().getNumero());
		contaCreditada.creditar(transferencia.getValor());
		contaCreditada.getMovimentacoes().add(transferencia);
		
		transferencia.setTipo(TipoMovimentacao.TRANSFERENCIA);
		transferencia.setConta(contaDebitada);
		transferencia.setContaDestino(contaCreditada);
		transferencia.setData(new Date());
		
		contaService.atualizar(contaDebitada);
		contaService.atualizar(contaCreditada);
		return movimentacaoRepository.save(transferencia);
	}

	@Override
	public Transferencia atualizar(Transferencia t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Long id) {
				
	}

	@Override
	public Transferencia pesquisarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transferencia> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
}