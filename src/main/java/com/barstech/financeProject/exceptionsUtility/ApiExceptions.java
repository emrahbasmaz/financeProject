package com.barstech.financeProject.exceptionsUtility;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ApiExceptions extends RuntimeException {
    public ApiExceptions(String message) {
        super(message);
    }
}