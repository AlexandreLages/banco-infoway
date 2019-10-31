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
import br.com.infoway.model.Transferencia;
import br.com.infoway.service.TransferenciaService;

@RestController
@RequestMapping(value="/transferencias", 
	produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
	consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class TransferenciaResource implements ResourceInterface<Transferencia> {

	@Autowired
	private TransferenciaService transferenciaService;
	
	@RequestMapping(method=RequestMethod.POST)
	@Override
	public ResponseEntity<Void> inserir(Transferencia t) {
		t = transferenciaService.inserir(t);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(t.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@Override
	public ResponseEntity<Void> atualizar(Transferencia t, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> deletar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Transferencia> pesquisarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<Transferencia>> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
}