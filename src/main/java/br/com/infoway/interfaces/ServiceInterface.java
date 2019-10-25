package br.com.infoway.interfaces;

import java.util.List;

public interface ServiceInterface<T> {

	public T inserir(T object);
	
	public T atualizar(T object);
	
	public void deletar(Long id);
	
	public T pesquisarPorId(Long id);
	
	public List<T> listarTodos();
}