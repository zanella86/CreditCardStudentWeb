package com.fiap.onescjr.creditcardintegrator.controller;

import com.fiap.onescjr.creditcardintegrator.dto.TransactionDTO;
import com.fiap.onescjr.creditcardintegrator.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDTO post(@RequestBody TransactionDTO transactionDTO) {
        return transactionService.insert(transactionDTO);
    }

    /*@PutMapping("")
    @ResponseStatus(HttpStatus.OK)
    public TransactionDTO put(@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO transactionDTOUpdated;
        try {
            transactionDTOUpdated = transactionService.update(id, transactionDTO);
        } catch(NoSuchElementException nseex) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return transactionDTOUpdated;
    }*/

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionDTO patch(@PathVariable Long id, @RequestBody TransactionDTO transactionDTO) {
        TransactionDTO transactionDTOUpdated;
        try {
            transactionDTOUpdated = transactionService.update(id, transactionDTO);
        } catch(NoSuchElementException nseex) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return transactionDTOUpdated;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public TransactionDTO get(@PathVariable Long id) {
        TransactionDTO transactionDTO;
        try {
            transactionDTO = transactionService.get(id).orElseThrow();
        } catch(NoSuchElementException nseex) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return transactionDTO;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDTO> list() {
        if(transactionService.list().isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return transactionService.list();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable Long id) {
        transactionService.delete(id);
    }
}
