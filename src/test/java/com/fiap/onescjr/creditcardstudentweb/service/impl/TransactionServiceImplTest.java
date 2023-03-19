package com.fiap.onescjr.creditcardstudentweb.service.impl;

import com.fiap.onescjr.creditcardstudentweb.dto.TransactionDTO;
import com.fiap.onescjr.creditcardstudentweb.entity.StudentEntity;
import com.fiap.onescjr.creditcardstudentweb.entity.TransactionEntity;
import com.fiap.onescjr.creditcardstudentweb.mapper.TransactionMapper;
import com.fiap.onescjr.creditcardstudentweb.repository.StudentRepository;
import com.fiap.onescjr.creditcardstudentweb.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceImplTest {
    @Mock
    TransactionRepository transactionRepository;
    @Mock
    StudentRepository studentRepository;
    @Mock
    TransactionMapper transactionMapper;
    @InjectMocks
    TransactionServiceImpl transactionServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        when(transactionMapper.convertDTOToEntity(any()))
                .thenReturn(getEntity());
        when(transactionMapper.convertEntityToDTO(any()))
                .thenReturn(null);
        when(studentRepository.getReferenceById(anyLong()))
                .thenReturn(getStudentEntity());

        TransactionDTO result = transactionServiceImpl.insert(getDTO());
        assertEquals(null, result);
    }

    @Test
    void testUpdate() {
        when(transactionMapper.convertEntityToDTO(any())).thenReturn(getDTO());
        when(transactionRepository.getReferenceById(anyLong())).thenReturn(getEntity());
        when(transactionRepository.save(any())).thenReturn(getEntity());
        when(studentRepository.getReferenceById(anyLong())).thenReturn(getStudentEntity());

        TransactionDTO result = transactionServiceImpl.update(anyLong(), getDTO());

        verify(transactionRepository, times(1)).getReferenceById(anyLong());
        verify(transactionRepository, times(1)).save(any());
        verify(transactionMapper, times(1)).convertEntityToDTO(any());
    }

    @Test
    void testUpdateThrowTransactionNotFound() {
        when(transactionRepository.getReferenceById(anyLong())).thenReturn(null);

        var exception = assertThrows(NoSuchElementException.class, () -> transactionServiceImpl.update(anyLong(), getDTO()));

        assertEquals("Transaction not found", exception.getMessage());
    }

    @Test
    void testGet() {
        when(transactionMapper.convertEntityToDTO(any())).thenReturn(getDTO());
        when(transactionRepository.getReferenceById(anyLong())).thenReturn(getEntity());

        Optional<TransactionDTO> result = transactionServiceImpl.get(any());

        verify(transactionMapper, times(1)).convertEntityToDTO(any());
        verify(transactionRepository, times(1)).getReferenceById(any());
    }

    @Test
    void testGetEmptyReturn() {
        when(transactionRepository.getReferenceById(any())).thenReturn(null);
        when(transactionMapper.convertEntityToDTO(any())).thenReturn(null);

        Optional<TransactionDTO> result = transactionServiceImpl.get(Long.valueOf(1));
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testList() {
        when(transactionRepository.listByStudent(anyLong(), any(), any())).thenReturn(List.of(getEntity()));
        when(transactionMapper.convertEntityToDTO(any())).thenReturn(getDTO());

        List<TransactionDTO> result = transactionServiceImpl
                .list(Long.valueOf(1),
                        LocalDateTime.of(2023, Month.MARCH, 15, 1, 15, 22),
                        LocalDateTime.of(2023, Month.MARCH, 15, 1, 15, 22));
        assertEquals(1, result.size());

        var transaction = result.stream().filter(tran -> tran.getPurchaseDescription().equals("purchaseDescription")).findFirst();
        assertNotNull(transaction.orElse(null));
    }

    @Test
    void testListEmptyReturn() {
        when(transactionRepository.listByStudent(anyLong(), any(), any())).thenReturn(null);

        List<TransactionDTO> result = transactionServiceImpl
                .list(Long.valueOf(1),
                        LocalDateTime.of(2023, Month.MARCH, 15, 1, 15, 22),
                        LocalDateTime.of(2023, Month.MARCH, 15, 1, 15, 22));

        assertEquals(0, result.size());
    }

    @Test
    void testDelete() {
        when(transactionRepository.getReferenceById(anyLong())).thenReturn(getEntity());

        transactionServiceImpl.delete(Long.valueOf(1));

        verify(transactionRepository, times(1)).getReferenceById(anyLong());
        verify(transactionRepository, times(1)).delete(any());
    }

    @Test
    void testDeleteWhenThrowNotFoundTransaction() {
        when(transactionRepository.getReferenceById(any())).thenReturn(null);

        Exception ex = assertThrows(NoSuchElementException.class, () -> {
            transactionServiceImpl.delete(Long.valueOf(1));
        });

        assertEquals("Transaction not found", ex.getMessage());
    }



    private TransactionDTO getDTO() {
        return TransactionDTO.builder()
                .value(BigDecimal.valueOf(51))
                .date(LocalDateTime.of(2023, Month.MARCH, 15, 1, 15, 22))
                .studentId(1L)
                .purchaseDescription("purchaseDescription")
                .build();
    }

    private StudentEntity getStudentEntity() {
        return StudentEntity.builder().id(1L).name("Laís").cardCode("").build();
    }

    private TransactionEntity getEntity() {
        return new TransactionEntity(
                Long.valueOf(1),
                "purchaseDescription",
                new BigDecimal(0),
                LocalDateTime.of(2023, Month.MARCH, 15, 1, 15, 22),
                new StudentEntity(Long.valueOf(1), "name", "cardCode", new HashSet<>()));
    }

}
