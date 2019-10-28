package br.com.infoway.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.infoway.interfaces.ResourceInterface;
import br.com.infoway.model.Conta;
import br.com.infoway.service.ContaService;

/**
 * 
 * @author Alexandre Lages
 * 
 * Implementação do resource de Conta
 */

@RestController
@RequestMapping(value="/contas")
public class ContaResource implements ResourceInterface<Conta> {

	@Autowired
	private ContaService contaService;
	
	/**
	 * Resource responsável por inserir uma conta
	 * @param conta
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Override
	public ResponseEntity<Void> inserir(@Valid Conta t) {
		t = contaService.inserir(t);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(t.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@Override
	public ResponseEntity<Void> atualizar(Conta t, Long id) {
		return null;
	}

	@Override
	public ResponseEntity<Void> deletar(Long id) {
		return null;
	}

	@Override
	public ResponseEntity<Conta> pesquisarPorId(Long id) {
		return null;
	}

	@Override
	public ResponseEntity<List<Conta>> listarTodos() {
		return null;
	}
}