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
import br.com.infoway.model.Banco;
import br.com.infoway.service.BancoService;

/**
 * 
 * @author Alexandre Lages
 *
 * Implementação de resources de Banco
 */

@RestController
@RequestMapping(value="/bancos", 
	produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
	consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class BancoResource implements ResourceInterface<Banco> {

	@Autowired
	private BancoService bancoService;

	/**
	 * Resource responsável por inserir um banco
	 * @param banco
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@Override
	public ResponseEntity<Void> inserir(@Valid Banco t) {
		t = bancoService.inserir(t);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(t.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	/**
	 * Resource responsável por atualizar um banco
	 * @param banco e id do banco que está sendo atualizado
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@Override
	public ResponseEntity<Void> atualizar(@Valid Banco t, Long id) {
		t.setId(id);
		t = bancoService.atualizar(t);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Resource responsável por deletar um banco
	 * @param id do banco que está sendo deletado
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@Override
	public ResponseEntity<Void> deletar(Long id) {
		bancoService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Resource responsável por pesquisar um banco
	 * @param id do banco pesquisado
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<Banco> pesquisarPorId(Long id) {
		Banco banco = bancoService.pesquisarPorId(id);
		return ResponseEntity.ok().body(banco);
	}

	/**
	 * Resource responsável por listar todos os bancos
	 * @param
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<Banco>> listarTodos() {
		List<Banco> listaBanco = bancoService.listarTodos();
		return ResponseEntity.ok().body(listaBanco);
	}
}