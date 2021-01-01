package com.kyle.springpractice.exception;

import com.kyle.springpractice.exception.dto.AlgorithmException;
import com.kyle.springpractice.exception.dto.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> exception(Exception e) {
        e.printStackTrace();

        ErrorMessage message = ErrorMessage.builder()
                .message(e.getLocalizedMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @ExceptionHandler(AlgorithmException.class)
    public ResponseEntity<ErrorMessage> algorithmException(AlgorithmException e) {
        ErrorMessage message = ErrorMessage.builder()
                .message(e.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorMessage> wrongParam(MissingServletRequestParameterException e) {
        ErrorMessage message = ErrorMessage.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorMessage> validationError(BindException e) {
        e.printStackTrace();

        ErrorMessage message = ErrorMessage.builder()
                .message("error filed: " + e.getFieldError().getField())
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> missMatchParameter(MethodArgumentTypeMismatchException e) {
        ErrorMessage message = ErrorMessage.builder()
                .message("error filed: " + e.getName())
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }


}
