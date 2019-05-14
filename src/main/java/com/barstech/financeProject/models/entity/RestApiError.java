package com.barstech.financeProject.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
@JsonTypeIdResolver(IdResolverUtility.class)
public class RestApiError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    private String debugMessage;

    private RestApiError() {
        timestamp = LocalDateTime.now();
    }

    public RestApiError(HttpStatus status) {

        this.status = status;
    }

    public RestApiError(HttpStatus status, Throwable ex) {

        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public RestApiError(HttpStatus status, String message, Throwable ex) {

        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

}

