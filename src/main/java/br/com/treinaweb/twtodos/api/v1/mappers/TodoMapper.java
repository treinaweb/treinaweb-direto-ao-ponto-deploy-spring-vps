package br.com.treinaweb.twtodos.api.v1.mappers;

import br.com.treinaweb.twtodos.api.v1.dtos.TodoRequest;
import br.com.treinaweb.twtodos.api.v1.dtos.TodoResponse;
import br.com.treinaweb.twtodos.core.models.Todo;

public interface TodoMapper {

    TodoResponse toTodoResponse(Todo todo);
    Todo toTodo(TodoRequest todoRequest);

}
