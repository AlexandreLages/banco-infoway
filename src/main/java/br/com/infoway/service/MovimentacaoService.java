package br.com.infoway.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infoway.dto.MovimentacaoDTO;
import br.com.infoway.enums.TipoMovimentacao;
import br.com.infoway.interfaces.ServiceInterface;
import br.com.infoway.model.Conta;
import br.com.infoway.model.Movimentacao;
import br.com.infoway.model.Transferencia;
import br.com.infoway.repository.TransferenciaRepository;

/**
 * 
 * @author Alexandre Lages
 * 
 * Implementação do service responsavel pela regra de negocios de Movimentação
 */
@Service
public class MovimentacaoService implements ServiceInterface<Movimentacao>{

	@Autowired
	private ContaService contaService;
	
	@Autowired
	private TransferenciaRepository transferenciaRepository;

	@Override
	public Movimentacao inserir(Movimentacao t) {
		return null;
	}

	@Override
	public Movimentacao atualizar(Movimentacao t) {
		return null;
	}

	@Override
	public void deletar(Long id) {
		
	}

	@Override
	public Movimentacao pesquisarPorId(Long id) {
		return null;
	}

	@Override
	public List<Movimentacao> listarTodos() {
		return null;
	}

	/**
	 * Metodo responsavel por listar todas as movimentacoes de uma conta bancaria
	 * @param numero da conta
	 * @return List<MovimentacaoDTO>
	 */
	public List<MovimentacaoDTO> listarTodos(Integer numero) {
		Conta conta = contaService.pesquisarPorNumero(numero);
		List<Movimentacao> listaMovimentacao = conta.getMovimentacoes();
		List<Transferencia> listaTransferencia = transferenciaRepository.findByContaDestino(conta);
		List<MovimentacaoDTO> listaMovimentacaoDTO = new ArrayList<>();
		MovimentacaoDTO movimentacaoDTO;
		for(Movimentacao m : listaMovimentacao) {
			if(m.getTipo() == TipoMovimentacao.TRANSFERENCIA) {
				Transferencia transferencia = (Transferencia) m;
				 movimentacaoDTO = new MovimentacaoDTO(m.getValor(), m.getConta().getNumero(), 
						 transferencia.getContaDestino().getNumero(), m.getTipo().getCodigo(), m.getData());
			} else {
				movimentacaoDTO = new MovimentacaoDTO(m.getValor(), m.getConta().getNumero(), 
						m.getConta().getNumero(), m.getTipo().getCodigo(), m.getData());
			}
			listaMovimentacaoDTO.add(movimentacaoDTO);
		}
		for(Transferencia t : listaTransferencia) {
			listaMovimentacaoDTO.add(new MovimentacaoDTO(t.getValor(), t.getConta().getNumero(), 
					t.getContaDestino().getNumero(), t.getTipo().getCodigo(), t.getData()));
		}
		return listaMovimentacaoDTO;
	}
}