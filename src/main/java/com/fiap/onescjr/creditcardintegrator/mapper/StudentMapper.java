package com.fiap.onescjr.creditcardintegrator.mapper;

import com.fiap.onescjr.creditcardintegrator.dto.StudentDTO;
import com.fiap.onescjr.creditcardintegrator.entity.StudentEntity;

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
