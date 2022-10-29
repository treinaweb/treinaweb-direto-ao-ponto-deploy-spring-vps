package br.com.treinaweb.twtodos.api.v1.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.twtodos.api.v1.dtos.TodoRequest;
import br.com.treinaweb.twtodos.api.v1.dtos.TodoResponse;
import br.com.treinaweb.twtodos.api.v1.services.TodoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todos")
public class TodoRestController {

    private final TodoService todoService;

    @GetMapping
    public List<TodoResponse> findAll() {
        return todoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@RequestBody @Valid TodoRequest todoRequest) {
        return todoService.create(todoRequest);
    }

    @GetMapping("/{id}")
    public TodoResponse findById(@PathVariable Long id) {
        return todoService.findById(id);
    }

    @PutMapping("/{id}")
    public TodoResponse updateById(
        @PathVariable Long id,
        @RequestBody @Valid TodoRequest todoRequest
    ) {
        return todoService.updateById(id, todoRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        todoService.deleteById(id);
    }

    @PatchMapping("/{id}/complete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void completeById(@PathVariable Long id) {
        todoService.completeById(id);
    }

}
