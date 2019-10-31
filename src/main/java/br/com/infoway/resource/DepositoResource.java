package br.com.infoway.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.infoway.interfaces.ResourceInterface;
import br.com.infoway.model.Deposito;
import br.com.infoway.service.DepositoService;

@RestController
@RequestMapping(value="/depositos", 
	produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
	consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class DepositoResource implements ResourceInterface<Deposito> {

	@Autowired
	private DepositoService depositoService;
	
	@RequestMapping(method=RequestMethod.POST)
	@Override
	public ResponseEntity<Void> inserir(Deposito t) {
		t = depositoService.inserir(t);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(t.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@Override
	public ResponseEntity<Void> atualizar(Deposito t, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> deletar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Deposito> pesquisarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<Deposito>> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
}