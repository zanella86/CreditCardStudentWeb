package com.fiap.onescjr.creditcardstudentweb.service.impl;

import com.fiap.onescjr.creditcardstudentweb.dto.ReportDTO;
import com.fiap.onescjr.creditcardstudentweb.dto.StudentDTO;
import com.fiap.onescjr.creditcardstudentweb.dto.TransactionDTO;
import com.fiap.onescjr.creditcardstudentweb.service.StudentService;
import com.fiap.onescjr.creditcardstudentweb.service.TransactionService;
import com.itextpdf.text.DocumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExtractPDFServiceImplTest {
    @Mock
    StudentService studentService;
    @Mock
    TransactionService transactionService;
    @InjectMocks
    ExtractPDFServiceImpl extractPDFServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateExtractPDF() throws DocumentException {
        byte[] result = extractPDFServiceImpl.createExtractPDF(getReportDTO());
        assertTrue(result.length > 0);
    }

    @Test
    void testExtractPDF() throws DocumentException {
        when(studentService.select(anyLong())).thenReturn(Optional.of(getStudent()));
        when(transactionService.list(anyLong(), any(), any())).thenReturn(List.of(getTransactionDTO()));

        byte[] result = extractPDFServiceImpl.extractPDF(Long.valueOf(1), getInitialDate(), getEndDate());

        assertTrue(result.length > 0);
    }

    @Test
    void testExtractPDFThrowsStudentNotFound() throws DocumentException {
        when(studentService.select(anyLong())).thenReturn(null);
        when(transactionService.list(anyLong(), any(), any())).thenReturn(new ArrayList<>());

        var exception = assertThrows(NoSuchElementException.class, () -> extractPDFServiceImpl.extractPDF(Long.valueOf(1), getInitialDate(), getEndDate()));

        assertEquals("Student not found", exception.getMessage());
    }

    @Test
    void testExtractPDFNotThrowWithEmptyList() throws DocumentException {
        when(studentService.select(anyLong())).thenReturn(Optional.of(getStudent()));
        when(transactionService.list(anyLong(), any(), any())).thenReturn(new ArrayList<>());

        byte[] result = extractPDFServiceImpl.extractPDF(Long.valueOf(1), getInitialDate(), getEndDate());
        assertTrue(Objects.nonNull(result));
    }

    private LocalDateTime getInitialDate() {
        return LocalDateTime.of(2023, Month.MARCH, 19, 16, 36, 53);
    }

    private LocalDateTime getEndDate() {
        return LocalDateTime.of(2023, Month.MARCH, 20, 16, 36, 53);
    }

    private StudentDTO getStudent() {
        return StudentDTO.builder()
                .id(1L)
                .name("student name").build();
    }

    private TransactionDTO getTransactionDTO() {
        return TransactionDTO.builder()
                .value(BigDecimal.valueOf(51))
                .date(LocalDateTime.of(2023, Month.MARCH, 15, 1, 15, 22))
                .studentId(1L)
                .purchaseDescription("purchaseDescription")
                .build();
    }

    private ReportDTO getReportDTO() {
        return ReportDTO.builder()
                .studentName(getStudent().getName())
                .transactions(List.of(getTransactionDTO()))
                .build();
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme