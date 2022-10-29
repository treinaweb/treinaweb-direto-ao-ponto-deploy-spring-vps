package br.com.treinaweb.twtodos.api.v1.dtos;

import java.time.LocalDateTime;

public record TodoResponse(
    Long id,
    String title,
    String description,
    Boolean completed,
    LocalDateTime dueDate,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}

