package br.com.treinaweb.twtodos.api.v1.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String path;
    private String cause;
    private String error;
    private Integer status;
    private String message;
    private LocalDateTime timestamp;

}
