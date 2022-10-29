package br.com.treinaweb.twtodos.api.v1.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record TodoRequest(
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    String title,

    @Size(min = 3, max = 255)
    String description,

    @NotNull
    @Future
    LocalDateTime dueDate
) {}
