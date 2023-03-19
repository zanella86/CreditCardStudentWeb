package com.fiap.onescjr.creditcardstudentweb.service.impl;

import com.fiap.onescjr.creditcardstudentweb.dto.StudentDTO;
import com.fiap.onescjr.creditcardstudentweb.mapper.StudentMapper;
import com.fiap.onescjr.creditcardstudentweb.repository.StudentRepository;
import com.fiap.onescjr.creditcardstudentweb.service.StudentService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;
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
        var studentEntity = studentRepository.findById(id).orElseThrow( () -> new NoSuchElementException("Student not found"));
        studentEntity.setName(studentDTO.getName());
        return studentMapper.convertEntityToDTO(studentRepository.save(studentEntity));
    }

    @Override
    public Optional<StudentDTO> select(Long id) throws NoSuchElementException {
        var student = studentRepository.findById(id);
        if(student.isEmpty())
            return Optional.empty();

        return Optional.of(studentMapper.convertEntityToDTO(student.get()));
    }

    @Override
    public List<StudentDTO> list() {
        var students = studentRepository.findAll();
        if(Objects.isNull(students))
            return new ArrayList<>();

        return students
                .stream()
                .map(studentMapper::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        var student = studentRepository.getReferenceById(id);
        if(Objects.isNull(student))
            throw new NoSuchElementException("Student not found");
        studentRepository.delete(student);
    }

}
