package com.fiap.onescjr.creditcardstudentweb.controller;

import com.fiap.onescjr.creditcardstudentweb.dto.StudentDTO;
import com.fiap.onescjr.creditcardstudentweb.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO post(@RequestBody StudentDTO studentDTO) {
        return studentService.insert(studentDTO);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO put(@RequestBody StudentDTO studentDTO) {
        StudentDTO studentDTOUpdated;
        try {
            studentDTOUpdated = studentService.update(studentDTO);
        } catch(NoSuchElementException nseex) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return studentDTOUpdated;
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO patch(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        StudentDTO studentDTOUpdated;
        try {
            studentDTOUpdated = studentService.update(id, studentDTO);
        } catch(NoSuchElementException nseex) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return studentDTOUpdated;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public StudentDTO get(@PathVariable Long id) {
        StudentDTO studentDTO;
        try {
            studentDTO = studentService.select(id).orElseThrow();
        } catch(NoSuchElementException nseex) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return studentDTO;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> list() {
        if(studentService.list().isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return studentService.list();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable Long id) {
        studentService.delete(id);
    }

}
