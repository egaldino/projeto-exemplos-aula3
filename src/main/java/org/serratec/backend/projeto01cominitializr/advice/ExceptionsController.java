package org.serratec.backend.projeto01cominitializr.advice;

import org.serratec.backend.projeto01cominitializr.exception.ParametroObrigatorioException;
import org.serratec.backend.projeto01cominitializr.exception.TodoNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsController {

	@ExceptionHandler(TodoNotFoundException.class)
	public ResponseEntity<Void> trataTodoNotFound(TodoNotFoundException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound()
							.header("x-erro-msg", mensagem)
							.build();

	}
	
	@ExceptionHandler(ParametroObrigatorioException.class)
	public ResponseEntity<Void> trataParametroObrigatorio(ParametroObrigatorioException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound()
							.header("x-erro-msg", mensagem)
							.build();

	}
}
