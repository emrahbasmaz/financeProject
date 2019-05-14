package com.barstech.financeProject.models.interfaces;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IResponsible {
    static <X> ResponseEntity<X> getResponseEntity(Optional<X> perhapsResponse) {
        return getResponseEntity(perhapsResponse, null);
    }

    static <X> ResponseEntity<X> getResponseEntity(Optional<X> perhapsResponse, HttpHeaders header) {
        return (ResponseEntity) perhapsResponse.map((response) ->
                (ResponseEntity.ok().headers(header)).body(response))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
}
