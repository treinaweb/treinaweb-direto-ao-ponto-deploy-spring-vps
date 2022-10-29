package br.com.treinaweb.twtodos.api.v1.mappers;

import org.springframework.stereotype.Service;

import br.com.treinaweb.twtodos.api.v1.dtos.TodoRequest;
import br.com.treinaweb.twtodos.api.v1.dtos.TodoResponse;
import br.com.treinaweb.twtodos.core.models.Todo;

@Service
public class TodoMapperImpl implements TodoMapper {

    @Override
    public TodoResponse toTodoResponse(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        return new TodoResponse(
            todo.getId(),
            todo.getTitle(),
            todo.getDescription(),
            todo.getCompleted(),
            todo.getDueDate(),
            todo.getCreatedAt(),
            todo.getUpdatedAt()
        );
    }

    @Override
    public Todo toTodo(TodoRequest todoRequest) {
        if ( todoRequest == null ) {
            return null;
        }

        return Todo.builder()
            .title( todoRequest.title() )
            .description( todoRequest.description() )
            .dueDate( todoRequest.dueDate() )
            .build();
    }

}
