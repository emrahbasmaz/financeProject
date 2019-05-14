package com.barstech.financeProject.exceptionsUtility;

import com.barstech.financeProject.models.entity.RestApiError;
import com.barstech.financeProject.services.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionsHandler extends ResponseEntityExceptionHandler {

    @Autowired
    TokenService tokenService;

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RestApiError restApiError = new RestApiError(BAD_REQUEST);
        restApiError.setMessage(String.format("Could not find that %s method for this URL %s", ex.getHttpMethod(), ex.getRequestURL()));
        restApiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(restApiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Invalid JSON request";
        return buildResponseEntity(new RestApiError(BAD_REQUEST, error, ex));
    }

    @ExceptionHandler(ApiExceptions.class)
    protected ResponseEntity<Object> handleGenericApiExceptions(ApiExceptions ex) {
        if ("Token Expired".equals(ex.getMessage()))
            tokenService.destroyToken();

        return buildResponseEntity(new RestApiError(HttpStatus.NOT_FOUND, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(RestApiError restApiError) {
        return new ResponseEntity<Object>(restApiError, restApiError.getStatus());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex) {
        return buildResponseEntity(new RestApiError(HttpStatus.NOT_FOUND, ex));
    }
}