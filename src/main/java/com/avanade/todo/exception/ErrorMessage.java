package com.avanade.todo.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private HttpStatus statusCode;
    private LocalDateTime timestamp;
    private String message;
    private String description;
}
