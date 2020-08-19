package org.serratec.backend.projeto01cominitializr.controller;

import java.util.List;

import org.serratec.backend.projeto01cominitializr.exception.ParametroObrigatorioException;
import org.serratec.backend.projeto01cominitializr.exception.TodoNotFoundException;
import org.serratec.backend.projeto01cominitializr.model.Todo;
import org.serratec.backend.projeto01cominitializr.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class TodoController {

	@Autowired
	private TodoService todoService;

	@PostMapping
	public ResponseEntity<Void> inserir(@RequestBody Todo todo) {
		todoService.inserir(todo);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Todo>> listar() {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(todoService.listar(), headers, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Todo> listarPorId(@PathVariable Integer id) throws TodoNotFoundException {
		Todo todo = todoService.listarPorId(id);

		if (todo != null) {
			return ResponseEntity.ok(todo);
		}
		return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> substituir(@PathVariable Integer id, @RequestBody(required = true) Todo todo)
			throws TodoNotFoundException, ParametroObrigatorioException {
		todoService.substituir(id, todo);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Integer id) throws TodoNotFoundException {
		todoService.deletar(id);
	}

}
