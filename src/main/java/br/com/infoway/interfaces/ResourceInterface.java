package br.com.infoway.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ResourceInterface<T> {

	public ResponseEntity<Void> inserir(@RequestBody T t);
	
	public ResponseEntity<Void> atualizar(@RequestBody T t, @PathVariable Long id);
	
	public ResponseEntity<Void> deletar(@PathVariable Long id);
	
	public ResponseEntity<T> pesquisarPorId(@PathVariable Long id);
	
	public ResponseEntity<List<T>> listarTodos();
}