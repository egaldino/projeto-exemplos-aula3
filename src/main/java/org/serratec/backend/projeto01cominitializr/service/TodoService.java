package org.serratec.backend.projeto01cominitializr.service;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.projeto01cominitializr.exception.ParametroObrigatorioException;
import org.serratec.backend.projeto01cominitializr.exception.TodoNotFoundException;
import org.serratec.backend.projeto01cominitializr.model.Todo;
import org.serratec.backend.projeto01cominitializr.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;
		
	public Todo inserir(Todo todo) {
		Todo todoSalvoNoBD = todoRepository.save(todo);
		return todoSalvoNoBD;
	}
	
	public List<Todo> listar() {
		return todoRepository.findAll();
	}
	
	public Todo listarPorId(Integer id) throws TodoNotFoundException {
		Optional<Todo> optionalTodo = todoRepository.findById(id);

		if(optionalTodo.isPresent()) {
			return optionalTodo.get();
		}
		
		throw new TodoNotFoundException("Todo com id " + id + " não encontrada");
	}
	
	public Todo substituir(Integer id, Todo todo) throws ParametroObrigatorioException, TodoNotFoundException {
		if(todo == null) throw new ParametroObrigatorioException("Campo 'todo' é obrigatório");
		
		Todo todoNoBanco = listarPorId(id);
		
		if(todo.getTitulo() != null) {
			todoNoBanco.setTitulo(todo.getTitulo());
		}
		
		if(todo.getDescricao() != null) {
			todoNoBanco.setDescricao(todo.getDescricao());
		}
		
		if(todo.getConcluido() != null) {
			todoNoBanco.setConcluido(todo.getConcluido());
		}
		
		if(todo.getData() != null) {
			todoNoBanco.setData(todo.getData());
		}
		
		return todoRepository.save(todoNoBanco);
	}
	
	public void deletar(Integer id) throws TodoNotFoundException {
		Todo todoNoBanco = listarPorId(id);
		todoRepository.delete(todoNoBanco);
	}
		
}
