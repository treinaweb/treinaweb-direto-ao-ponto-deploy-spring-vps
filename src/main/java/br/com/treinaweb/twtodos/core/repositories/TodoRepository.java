package br.com.treinaweb.twtodos.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.twtodos.core.models.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
