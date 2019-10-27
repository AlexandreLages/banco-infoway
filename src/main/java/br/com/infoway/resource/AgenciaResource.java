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

/**
 * 
 * @author Alexandre Lages
 *
 * Implementação dos resources de Agência
 */

@RestController
@RequestMapping(value="/agencias")
public class AgenciaResource implements ResourceInterface<Agencia> {
	
	@Autowired
	private AgenciaService agenciaService;

	/**
	 * Resource responsável por inserir uma agência
	 * @param agencia
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Override	
	public ResponseEntity<Void> inserir(Agencia agencia) {
		agencia = agenciaService.inserir(agencia);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(agencia.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * Resource responsável por atualizar uma agência
	 * @param agencia
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.PUT,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Override
	public ResponseEntity<Void> atualizar(Agencia t, Long id) {
		t.setId(id);
		t = agenciaService.atualizar(t);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Resource responsável por deletar uma agência
	 * @param id da agência
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Override
	public ResponseEntity<Void> deletar(Long id) {
		agenciaService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Resource responsável por pesquisar uma agência
	 * @param id da agência
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Override
	public ResponseEntity<Agencia> pesquisarPorId(Long id) {
		Agencia agencia = agenciaService.pesquisarPorId(id);
		return ResponseEntity.ok().body(agencia);
	}

	/**
	 * Resource responsável por listar todas as agências
	 * @param
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@Override
	public ResponseEntity<List<Agencia>> listarTodos() {
		List<Agencia> listaAgencia = agenciaService.listarTodos();
		return ResponseEntity.ok().body(listaAgencia);
	}
}