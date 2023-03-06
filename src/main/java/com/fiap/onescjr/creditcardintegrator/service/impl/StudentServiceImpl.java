package com.fiap.onescjr.creditcardintegrator.service.impl;

import com.fiap.onescjr.creditcardintegrator.dto.StudentDTO;
import com.fiap.onescjr.creditcardintegrator.entity.StudentEntity;
import com.fiap.onescjr.creditcardintegrator.mapper.StudentMapper;
import com.fiap.onescjr.creditcardintegrator.repository.StudentRepository;
import com.fiap.onescjr.creditcardintegrator.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentDTO insert(StudentDTO studentDTO) {
        var studentEntitySaved = studentRepository.save(studentMapper.convertDTOToEntity(studentDTO));
        return studentMapper.convertEntityToDTO(studentEntitySaved);
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO) throws NoSuchElementException {
        return studentMapper.convertEntityToDTO(
                studentRepository.save(
                        studentMapper.convertDTOToEntity(studentDTO)
                )
        );
    }

    @Override
    public StudentDTO update(Long id, StudentDTO studentDTO) throws NoSuchElementException {
        var studentEntity = studentRepository.findById(id).orElseThrow();
        studentEntity.setName(studentDTO.getName());
        return studentMapper.convertEntityToDTO(studentRepository.save(studentEntity));
    }

    @Override
    public Optional<StudentDTO> select(Long id) throws NoSuchElementException {
        return Optional.ofNullable(
                studentMapper.convertEntityToDTO(
                        studentRepository.findById(id).orElseThrow()
                )
        );
    }

    @Override
    public List<StudentDTO> list() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

}
