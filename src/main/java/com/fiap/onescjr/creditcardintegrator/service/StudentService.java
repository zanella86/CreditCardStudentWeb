package com.fiap.onescjr.creditcardintegrator.service;

import com.fiap.onescjr.creditcardintegrator.dto.StudentDTO;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface StudentService {
    @Transactional
    StudentDTO insert(StudentDTO studentDTO);

    @Transactional
    StudentDTO update(StudentDTO studentDTO) throws NoSuchElementException;

    @Transactional
    StudentDTO update(Long id, StudentDTO studentDTO) throws NoSuchElementException;

    Optional<StudentDTO> select(Long id) throws NoSuchElementException;

    List<StudentDTO> list();

    @Transactional
    void delete(Long id);

}
