package br.com.treinaweb.twtodos.api.v1.services;

import java.util.List;

import br.com.treinaweb.twtodos.api.v1.dtos.TodoRequest;
import br.com.treinaweb.twtodos.api.v1.dtos.TodoResponse;

public interface TodoService {

    List<TodoResponse> findAll();
    TodoResponse create(TodoRequest todoRequest);
    TodoResponse findById(Long id);
    TodoResponse updateById(Long id, TodoRequest todoRequest);
    void deleteById(Long id);
    void completeById(Long id);

}
