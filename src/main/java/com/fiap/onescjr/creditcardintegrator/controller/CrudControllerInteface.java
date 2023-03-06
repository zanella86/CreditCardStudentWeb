package com.fiap.onescjr.creditcardintegrator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface CrudControllerInteface<D> {    // TODO: P.O.C

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    D post(@RequestBody D dto);

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    D put(@RequestBody D dto);

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    D patch(@PathVariable Long id, @RequestBody D dto);

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    D get(@PathVariable Long id) throws ResponseStatusException;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<D> list() throws ResponseStatusException;

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    void remove(@PathVariable Long id);

}
