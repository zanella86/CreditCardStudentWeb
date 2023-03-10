package com.fiap.onescjr.creditcardintegrator.service.impl;

import com.fiap.onescjr.creditcardintegrator.dto.TransactionDTO;
import com.fiap.onescjr.creditcardintegrator.entity.TransactionEntity;
import com.fiap.onescjr.creditcardintegrator.mapper.TransactionMapper;
import com.fiap.onescjr.creditcardintegrator.repository.TransactionRepository;
import com.fiap.onescjr.creditcardintegrator.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDTO insert(TransactionDTO transactionDTO) {
        TransactionEntity entity = transactionRepository.save(TransactionMapper.convertDTOToEntity(transactionDTO));
        //todo - validar a parte de salvar o student
        return TransactionMapper.convertEntityToDTO(entity);
    }

    @Override
    public TransactionDTO update(Long id, TransactionDTO transactionDTO) throws NoSuchElementException {
        TransactionEntity transaction = transactionRepository.getReferenceById(id);
        Optional.ofNullable(transaction).orElseThrow(() -> { throw new NoSuchElementException("");});

        transactionRepository.save(TransactionMapper.convertDTOToEntity(transactionDTO));
        return transactionDTO;
    }

    @Override
    public Optional<TransactionDTO> get(Long id) throws NoSuchElementException {
        //todo - validar aqui
        return Optional.ofNullable(TransactionMapper.convertEntityToDTO(transactionRepository.getReferenceById(id)));
    }

    @Override
    public List<TransactionDTO> list() {
        return transactionRepository.findAll()
                .stream()
                .map(TransactionMapper::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> list(LocalDateTime initial, LocalDateTime end) {
        return transactionRepository.list(initial, end)
                .stream()
                .map(TransactionMapper::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> listByStudent(Long studentId, LocalDateTime initial, LocalDateTime end) {
        return transactionRepository.listByStudent(studentId, initial, end)
                .stream()
                .map(TransactionMapper::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        TransactionEntity transaction = transactionRepository.getReferenceById(id);
        transactionRepository.delete(transaction);
    }


}
