package com.fiap.onescjr.creditcardintegrator.service;

import com.fiap.onescjr.creditcardintegrator.dto.TransactionDTO;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface TransactionService {
    @Transactional
    TransactionDTO insert(TransactionDTO transactionDTO);

    @Transactional
    TransactionDTO update(Long id, TransactionDTO transactionDTO) throws NoSuchElementException;

    Optional<TransactionDTO> get(Long id) throws NoSuchElementException;

    List<TransactionDTO> list();

    List<TransactionDTO> list(LocalDateTime initial, LocalDateTime end);

    List<TransactionDTO> listByStudent(Long studentId, LocalDateTime initial, LocalDateTime end);

    @Transactional
    void delete(Long id);
}
