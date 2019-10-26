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
import br.com.infoway.model.Agencia;
import br.com.infoway.service.AgenciaService;

@RestController
@RequestMapping(value="/agencias")
public class AgenciaResource implements ResourceInterface<Agencia> {
	
	@Autowired
	private AgenciaService agenciaService;

	@RequestMapping(method=RequestMethod.POST, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Override
	public ResponseEntity<Void> inserir(Agencia t) {
		t = agenciaService.inserir(t);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(t.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Override
	public ResponseEntity<Void> atualizar(Agencia t, Long id) {
		t.setId(id);
		t = agenciaService.atualizar(t);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Override
	public ResponseEntity<Void> deletar(Long id) {
		agenciaService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Override
	public ResponseEntity<Agencia> pesquisarPorId(Long id) {
		Agencia agencia = agenciaService.pesquisarPorId(id);
		return ResponseEntity.ok().body(agencia);
	}

	@RequestMapping(method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Override
	public ResponseEntity<List<Agencia>> listarTodos() {
		List<Agencia> listaAgencia = agenciaService.listarTodos();
		return ResponseEntity.ok().body(listaAgencia);
	}
}