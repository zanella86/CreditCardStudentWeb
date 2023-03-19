package com.fiap.onescjr.creditcardstudentweb.service.impl;

import com.fiap.onescjr.creditcardstudentweb.dto.StudentDTO;
import com.fiap.onescjr.creditcardstudentweb.entity.StudentEntity;
import com.fiap.onescjr.creditcardstudentweb.entity.TransactionEntity;
import com.fiap.onescjr.creditcardstudentweb.mapper.StudentMapper;
import com.fiap.onescjr.creditcardstudentweb.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {
    @Mock
    StudentRepository studentRepository;
    @Mock
    StudentMapper studentMapper;
    @InjectMocks
    StudentServiceImpl studentServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        when(studentMapper.convertDTOToEntity(any())).thenReturn(getEntity());
        when(studentMapper.convertEntityToDTO(any())).thenReturn(getDTO());

        StudentDTO result = studentServiceImpl.insert(getDTO());

        verify(studentRepository, times(1)).save(any());
        verify(studentMapper, times(1)).convertDTOToEntity(any());
        verify(studentMapper, times(1)).convertEntityToDTO(any());
    }

    @Test
    void testUpdate() {
        when(studentMapper.convertDTOToEntity(any())).thenReturn(getEntity());
        when(studentMapper.convertEntityToDTO(any())).thenReturn(getDTO());

        StudentDTO result = studentServiceImpl.update(getDTO());

        verify(studentMapper, times(1)).convertEntityToDTO(any());
        verify(studentMapper, times(1)).convertDTOToEntity(any());
        verify(studentRepository, times(1)).save(any());
    }

    @Test
    void testUpdate2() {
        when(studentMapper.convertEntityToDTO(any())).thenReturn(getDTO());
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(getEntity()));

        StudentDTO result = studentServiceImpl.update(anyLong(), getDTO());

        verify(studentRepository, times(1)).findById(anyLong());
        verify(studentMapper, times(1)).convertEntityToDTO(any());
    }

    @Test
    void testUpdate2ThrowNoStudentFound() {
        when(studentMapper.convertEntityToDTO(any())).thenReturn(getDTO());
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        var exception = assertThrows(NoSuchElementException.class, () -> studentServiceImpl.update(anyLong(), getDTO()));

        assertEquals("Student not found", exception.getMessage());
    }

    @Test
    void testSelect() {
        when(studentMapper.convertEntityToDTO(any())).thenReturn(getDTO());
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(getEntity()));

        Optional<StudentDTO> result = studentServiceImpl.select(anyLong());

        verify(studentRepository, times(1)).findById(anyLong());
        assertTrue(result.isPresent());
    }

    @Test
    void testSelectEmpty() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<StudentDTO> result = studentServiceImpl.select(anyLong());

        verify(studentRepository, times(1)).findById(anyLong());
        assertTrue(result.isEmpty());
    }

    @Test
    void testList() {
        when(studentRepository.findAll()).thenReturn(List.of(getEntity()));

        List<StudentDTO> result = studentServiceImpl.list();

        assertEquals(1, result.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testEmptyList() {
        when(studentRepository.findAll()).thenReturn(null);

        List<StudentDTO> result = studentServiceImpl.list();

        assertEquals(0, result.size());
    }

    @Test
    void testDelete() {
        when(studentRepository.getReferenceById(anyLong())).thenReturn(getEntity());

        studentServiceImpl.delete(anyLong());

        verify(studentRepository, times(1)).getReferenceById(anyLong());
        verify(studentRepository, times(1)).delete(any());
    }

    @Test
    void testDeleteThrowNotFound() {
        when(studentRepository.getReferenceById(anyLong())).thenReturn(null);

        var exception = assertThrows(NoSuchElementException.class, () -> studentServiceImpl.delete(anyLong()));

        assertEquals("Student not found", exception.getMessage());
    }



    private StudentEntity getEntity() {
        return StudentEntity.builder()
                .id(1L)
                .name("name student")
                .cardCode("1544 84")
                .build();
    }

    private StudentDTO getDTO() {
        return StudentDTO.builder()
                .id(1L)
                .name("name student")
                .build();
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme