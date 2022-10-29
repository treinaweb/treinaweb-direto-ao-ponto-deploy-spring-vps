package br.com.treinaweb.twtodos.api.v1.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.treinaweb.twtodos.api.v1.dtos.TodoRequest;
import br.com.treinaweb.twtodos.api.v1.dtos.TodoResponse;
import br.com.treinaweb.twtodos.api.v1.mappers.TodoMapper;
import br.com.treinaweb.twtodos.core.exceptions.TodoNotFoundException;
import br.com.treinaweb.twtodos.core.repositories.TodoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoMapper todoMapper;
    private final TodoRepository todoRepository;

    @Override
    public List<TodoResponse> findAll() {
        return todoRepository.findAll()
            .stream()
            .map(todoMapper::toTodoResponse)
            .toList();
    }

    @Override
    public TodoResponse create(TodoRequest todoRequest) {
        var todo = todoMapper.toTodo(todoRequest);
        var savedTodo = todoRepository.save(todo);
        return todoMapper.toTodoResponse(savedTodo);
    }

    @Override
    public TodoResponse findById(Long id) {
        return todoRepository.findById(id)
            .map(todoMapper::toTodoResponse)
            .orElseThrow(TodoNotFoundException::new);
    }

    @Override
    public TodoResponse updateById(Long id, TodoRequest todoRequest) {
        var todo = todoRepository.findById(id)
            .orElseThrow(TodoNotFoundException::new);
        BeanUtils.copyProperties(todoRequest, todo);
        var savedTodo = todoRepository.save(todo);
        return todoMapper.toTodoResponse(savedTodo);
    }

    @Override
    public void deleteById(Long id) {
        var todo = todoRepository.findById(id)
            .orElseThrow(TodoNotFoundException::new);
        todoRepository.delete(todo);
    }

    @Override
    public void completeById(Long id) {
        var todo = todoRepository.findById(id)
            .orElseThrow(TodoNotFoundException::new);
        todo.setCompleted(true);
        todoRepository.save(todo);
    }

}
