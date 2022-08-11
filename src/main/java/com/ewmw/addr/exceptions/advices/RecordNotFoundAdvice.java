package com.ewmw.addr.exceptions.advices;

import com.ewmw.addr.exceptions.responses.RecordNotFoundResponse;
import com.ewmw.addr.exceptions.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RecordNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(RecordNotFoundException.class)
    ResponseEntity<RecordNotFoundResponse> handle(RecordNotFoundException ex) {
        RecordNotFoundResponse response = new RecordNotFoundResponse(ex.getIdentifier(), ex.getModelName());

        return new ResponseEntity<>(response, response.getResponseStatusCode());
    }


}
