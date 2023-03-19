package com.fiap.onescjr.creditcardstudentweb.controller;

import com.fiap.onescjr.creditcardstudentweb.dto.TransactionDTO;
import com.fiap.onescjr.creditcardstudentweb.service.TransactionService;
import jakarta.validation.constraints.NotNull;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.LocalDateTime;
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
    public ResponseEntity<Object> post(@RequestBody TransactionDTO transactionDTO) {
        var response = transactionService.insert(transactionDTO);
        return ResponseEntity.created(URI.create("/transaction/" + response.getId())).body(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Object> patch(@PathVariable Long id, @RequestBody TransactionDTO transactionDTO) {
        var transactionDTOUpdated = transactionService.update(id, transactionDTO);
        return ResponseEntity.ok(transactionDTOUpdated);
    }

    @GetMapping("{id}")
    public ResponseEntity<TransactionDTO> get(@PathVariable Long id) {
        var transactionDTO = transactionService.get(id).orElseThrow();
        return ResponseEntity.ok().body(transactionDTO);
    }

    @GetMapping
    public ResponseEntity<Object> list(
            @RequestParam(name = "initialDate") @NotNull LocalDateTime initialDate,
            @RequestParam(name = "finalDate") @NotNull LocalDateTime finalDate,
            @RequestParam(name = "studentId") @NotNull Long id) {

        var transactions = transactionService.list(id, initialDate, finalDate);
        if(transactions.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(transactions);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable Long id) {
        transactionService.delete(id);
    }
}
