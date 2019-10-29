package br.com.infoway.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.enums.TipoMovimentacao;
import br.com.infoway.interfaces.ServiceInterface;
import br.com.infoway.model.Conta;
import br.com.infoway.model.Transferencia;
import br.com.infoway.repository.MovimentacaoRepository;

/**
 * 
 * @author Alexandre Lages
 * 
 * Implementação do service de Movimentação
 */
@Service
public class TransferenciaService implements ServiceInterface<Transferencia>{

	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	private ContaService contaService;

	@Override
	public Transferencia inserir(Transferencia t) {
		Conta contaDebitada = contaService.pesquisarPorNumero(t.getConta().getNumero());
		contaDebitada.saque(t.getValor());
		contaDebitada.getMovimentacoes().add(t);
		
		Conta contaCreditada = contaService.pesquisarPorNumero(t.getContaDestino().getNumero());
		contaCreditada.deposito(t.getValor());
		contaCreditada.getMovimentacoes().add(t);
		
		t.setTipo(TipoMovimentacao.TRANSFERENCIA);
		t.setConta(contaDebitada);
		t.setContaDestino(contaCreditada);
		t.setData(new Date());
		
		contaService.atualizar(contaDebitada);
		contaService.atualizar(contaCreditada);
		return movimentacaoRepository.save(t);
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