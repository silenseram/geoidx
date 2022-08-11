package com.ewmw.addr.exceptions.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {
    @Getter
    protected String title = "Error caused";

    @Getter
    protected String message = "Something went wrong";

    @Getter
    protected HttpStatus responseStatusCode = HttpStatus.BAD_REQUEST;

    @Getter
    protected ArrayList<String> details;

    @Getter
    protected String errorCode;

    @Getter
    protected String path;
}
