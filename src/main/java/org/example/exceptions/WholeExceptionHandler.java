package org.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WholeExceptionHandler extends Exception{

    @ExceptionHandler(LinkNotFoundException.class)
    public ResponseEntity<String> handleLinkNotFoundException(LinkNotFoundException l)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(l.getMessage());
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<String> handleInvalidRequestException(InvalidRequestException i)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(i.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> otherExceptions(Exception e)
    {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
}
