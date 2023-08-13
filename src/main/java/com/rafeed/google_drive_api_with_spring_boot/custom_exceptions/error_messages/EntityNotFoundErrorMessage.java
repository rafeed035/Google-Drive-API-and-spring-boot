package com.rafeed.google_drive_api_with_spring_boot.custom_exceptions.error_messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityNotFoundErrorMessage {
    private HttpStatus httpStatus;
    private String message;
}
