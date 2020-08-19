package org.serratec.backend.projeto01cominitializr.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.projeto01cominitializr.exception.ParametroObrigatorioException;
import org.serratec.backend.projeto01cominitializr.exception.TodoNotFoundException;
import org.serratec.backend.projeto01cominitializr.model.Todo;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private List<Todo> todosRepository = new ArrayList<>();
	
	public void inserir(Todo todo) {		
		todo.setId(todosRepository.size() + 1);
		this.todosRepository.add(todo);
	}
	
	public List<Todo> listar() {
		return this.todosRepository;
	}
	
	public Todo listarPorId(Integer id) throws TodoNotFoundException {
		for (Todo todo : todosRepository) {
			if(todo.getId().equals(id)) {
				return todo;
			}
		}
		throw new TodoNotFoundException("Todo com id " + id + " não encontrada");
	}
	
	public void substituir(Integer id, Todo todo) throws ParametroObrigatorioException, TodoNotFoundException {
		if(todo == null) throw new ParametroObrigatorioException("Campo 'todo' é obrigatório");
		
		Todo todoNaLista = listarPorId(id);
		
		if(todo.getTitulo() != null) {
			todoNaLista.setTitulo(todo.getTitulo());
		}
		
		if(todo.getDescricao() != null) {
			todoNaLista.setDescricao(todo.getDescricao());
		}
		
		if(todo.getConcluido() != null) {
			todoNaLista.setConcluido(todo.getConcluido());
		}
		
		if(todo.getData() != null) {
			todoNaLista.setData(todo.getData());
		}
	}
	
	public void deletar(Integer id) throws TodoNotFoundException {
		Todo todoNaLista = listarPorId(id);
		todosRepository.remove(todoNaLista);
	}
		
}
