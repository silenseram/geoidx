package com.ewmw.addr.exceptions.advices;

import com.ewmw.addr.exceptions.responses.NotFoundPathResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ControllerAdvice
public class PathNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(NoHandlerFoundException.class)
    ResponseEntity<NotFoundPathResponse> handle(NoHandlerFoundException ex) {
        NotFoundPathResponse response = new NotFoundPathResponse(ex.getRequestURL());

        return new ResponseEntity<>(response, response.getResponseStatusCode());
    }

}
