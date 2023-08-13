package com.rafeed.google_drive_api_with_spring_boot.custom_exceptions.exception_handler;

import com.rafeed.google_drive_api_with_spring_boot.custom_exceptions.error_messages.EntityAlreadyExistsErrorMessage;
import com.rafeed.google_drive_api_with_spring_boot.custom_exceptions.error_messages.EntityNotFoundErrorMessage;
import com.rafeed.google_drive_api_with_spring_boot.custom_exceptions.exceptions.EntityAlreadyExistsException;
import com.rafeed.google_drive_api_with_spring_boot.custom_exceptions.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    //exception handler for entity already exists exception
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<EntityAlreadyExistsErrorMessage> entityAlreadyExistsException(EntityAlreadyExistsException entityAlreadyExistsException,
                                                                                        WebRequest webRequest){
        EntityAlreadyExistsErrorMessage entityAlreadyExistsErrorMessage = new EntityAlreadyExistsErrorMessage(HttpStatus.CONFLICT, entityAlreadyExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(entityAlreadyExistsErrorMessage);
    }

    //exception handler for entity not found exception
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<EntityNotFoundErrorMessage> entityNotFoundException(EntityNotFoundException entityNotFoundException,
                                                                              WebRequest webRequest){
        EntityNotFoundErrorMessage entityNotFoundErrorMessage = new EntityNotFoundErrorMessage(HttpStatus.NOT_FOUND, entityNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFoundErrorMessage);
    }
}
