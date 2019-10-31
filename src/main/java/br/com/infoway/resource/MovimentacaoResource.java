package br.com.infoway.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.infoway.dto.MovimentacaoDTO;
import br.com.infoway.interfaces.ResourceInterface;
import br.com.infoway.model.Movimentacao;
import br.com.infoway.service.MovimentacaoService;

@RestController
@RequestMapping(value="/extratos",
	produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
	consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class MovimentacaoResource implements ResourceInterface<Movimentacao> {

	@Autowired
	private MovimentacaoService movimentacaoService;

	@Override
	public ResponseEntity<Void> inserir(Movimentacao t) {
		return null;
	}

	@Override
	public ResponseEntity<Void> atualizar(Movimentacao t, Long id) {
		return null;
	}

	@Override
	public ResponseEntity<Void> deletar(Long id) {
		return null;
	}

	@Override
	public ResponseEntity<Movimentacao> pesquisarPorId(Long id) {
		return null;
	}

	@Override
	public ResponseEntity<List<Movimentacao>> listarTodos() {
		return null;
	}
	
	/**
	 * Resource responsável por listar todas as movimentações de uma conta
	 * @param numero da conta
	 * @return
	 */
	@RequestMapping(value="/{numero}", method=RequestMethod.GET)
	public ResponseEntity<List<MovimentacaoDTO>> listarTodos(@PathVariable Integer numero) {
		List<MovimentacaoDTO> listaMovimentacaoDTO = movimentacaoService.listarTodos(numero);
		return ResponseEntity.ok().body(listaMovimentacaoDTO);
	}
}