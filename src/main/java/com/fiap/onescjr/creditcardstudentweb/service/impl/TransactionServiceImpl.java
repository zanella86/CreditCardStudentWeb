package com.fiap.onescjr.creditcardstudentweb.service.impl;

import com.fiap.onescjr.creditcardstudentweb.dto.TransactionDTO;
import com.fiap.onescjr.creditcardstudentweb.entity.StudentEntity;
import com.fiap.onescjr.creditcardstudentweb.entity.TransactionEntity;
import com.fiap.onescjr.creditcardstudentweb.mapper.TransactionMapper;
import com.fiap.onescjr.creditcardstudentweb.repository.StudentRepository;
import com.fiap.onescjr.creditcardstudentweb.repository.TransactionRepository;
import com.fiap.onescjr.creditcardstudentweb.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final StudentRepository studentRepository;
    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, StudentRepository studentRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.studentRepository = studentRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionDTO insert(TransactionDTO transactionDTO) {
        var student = getStudent(transactionDTO);

        TransactionEntity entity = transactionRepository.save(prepareTransactionToSave(transactionDTO, student));
        return transactionMapper.convertEntityToDTO(entity);
    }

    @Override
    public TransactionDTO update(Long id, TransactionDTO transactionDTO) throws NoSuchElementException {
        TransactionEntity transaction = transactionRepository.getReferenceById(id);
        Optional.ofNullable(transaction).orElseThrow(() -> { throw new NoSuchElementException("");});

        TransactionEntity entity = transactionRepository.save(updateValues(transaction, transactionDTO, getStudent(transactionDTO)));
        return transactionMapper.convertEntityToDTO(entity);
    }

    @Override
    public Optional<TransactionDTO> get(Long id) throws NoSuchElementException {
        var transaction = transactionRepository.getReferenceById(id);
        if(transaction == null)
            Optional.empty();
        return Optional.ofNullable(transactionMapper.convertEntityToDTO(transaction));
    }


    @Override
    public List<TransactionDTO> list(Long studentId, LocalDateTime initial, LocalDateTime end) {
        var transactions = transactionRepository.listByStudent(studentId, initial, end);

        if(Objects.isNull(transactions) || transactions.isEmpty())
            return new ArrayList<>();

        return transactionRepository.listByStudent(studentId, initial, end)
                .stream()
                .map(transactionMapper::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        TransactionEntity transaction = transactionRepository.getReferenceById(id);
        if(Objects.isNull(transaction))
            throw new NoSuchElementException("Transaction not found");
        transactionRepository.delete(transaction);
    }


    private StudentEntity getStudent(TransactionDTO transactionDTO) {
        var student = studentRepository.getReferenceById(transactionDTO.getStudentId());
        if(Objects.isNull(student))
            throw new NoSuchElementException("Student not found");

        return student;
    }
    private TransactionEntity prepareTransactionToSave(TransactionDTO transactionDTO, StudentEntity student) {
        var transaction = transactionMapper.convertDTOToEntity(transactionDTO);
        transaction.setStudent(student);

        return transaction;
    }

    private TransactionEntity updateValues(TransactionEntity entity, TransactionDTO transactionDTO, StudentEntity student) {
        entity.setStudent(student);
        entity.setValue(transactionDTO.getValue());
        entity.setDate(transactionDTO.getDate());
        entity.setPurchaseDescription(transactionDTO.getPurchaseDescription());

        return entity;
    }
}
