package com.fiap.onescjr.creditcardstudentweb.mapper;

import com.fiap.onescjr.creditcardstudentweb.dto.StudentDTO;
import com.fiap.onescjr.creditcardstudentweb.entity.StudentEntity;

public class StudentMapper implements Mapper<StudentDTO, StudentEntity> {

    @Override
    public StudentEntity convertDTOToEntity(StudentDTO dto) {
        return StudentEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    @Override
    public StudentDTO convertEntityToDTO(StudentEntity entity) {
        return StudentDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

}
