package com.fiap.onescjr.creditcardintegrator.mapper;

import com.fiap.onescjr.creditcardintegrator.dto.TransactionDTO;
import com.fiap.onescjr.creditcardintegrator.entity.StudentEntity;
import com.fiap.onescjr.creditcardintegrator.entity.TransactionEntity;


public final class TransactionMapper {

    public static TransactionEntity convertDTOToEntity(TransactionDTO dto) {
        return TransactionEntity.builder()
                .id(dto.getId())
                .date(dto.getDate())
                .purchaseDescription(dto.getPurchaseDescription())
                .value(dto.getValue())
                .build();
    }

    public static TransactionEntity convertDTOToEntity(TransactionDTO dto, StudentEntity student) {
        var transaction = convertDTOToEntity(dto);
        transaction.setStudent(student);
        return transaction;
    }


    public static TransactionDTO convertEntityToDTO(TransactionEntity entity) {
        return TransactionDTO.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .purchaseDescription(entity.getPurchaseDescription())
                .studentId(entity.getStudent().getId())
                .build();
    }
}
