package com.ewmw.addr.exceptions.advices;

import com.ewmw.addr.exceptions.responses.MethodArgumentNotValidResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MethodArgumentNotValidAdvice {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<MethodArgumentNotValidResponse> handle(MethodArgumentNotValidException ex) {
        MethodArgumentNotValidResponse response = new MethodArgumentNotValidResponse(
                ex.getFieldError().getField(),
                ex.getFieldError().getDefaultMessage()
        );

        return new ResponseEntity<>(response, response.getResponseStatusCode());
    }
}
