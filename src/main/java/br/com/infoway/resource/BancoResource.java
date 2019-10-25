package br.com.infoway.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.infoway.interfaces.ResourceInterface;
import br.com.infoway.model.Banco;
import br.com.infoway.service.BancoService;

@RestController
@RequestMapping(value="/bancos")
public class BancoResource implements ResourceInterface<Banco> {

	@Autowired
	private BancoService bancoService;

	@RequestMapping(method=RequestMethod.POST)
	@Override
	public ResponseEntity<Void> inserir(Banco t) {
		t = bancoService.inserir(t);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(t.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@Override
	public ResponseEntity<Void> atualizar(Banco t, Long id) {
		t.setId(id);
		t = bancoService.atualizar(t);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@Override
	public ResponseEntity<Void> deletar(Long id) {
		bancoService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<Banco> pesquisarPorId(Long id) {
		Banco banco = bancoService.pesquisarPorId(id);
		return ResponseEntity.ok().body(banco);
	}

	@RequestMapping(method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<Banco>> listarTodos() {
		List<Banco> listaBanco = bancoService.listarTodos();
		return ResponseEntity.ok().body(listaBanco);
	}
}