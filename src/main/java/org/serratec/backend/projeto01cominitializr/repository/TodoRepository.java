package org.serratec.backend.projeto01cominitializr.repository;

import org.serratec.backend.projeto01cominitializr.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

}
