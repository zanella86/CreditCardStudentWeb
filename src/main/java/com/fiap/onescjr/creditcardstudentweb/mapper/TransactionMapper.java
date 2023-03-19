package com.fiap.onescjr.creditcardstudentweb.mapper;

import com.fiap.onescjr.creditcardstudentweb.dto.TransactionDTO;
import com.fiap.onescjr.creditcardstudentweb.entity.StudentEntity;
import com.fiap.onescjr.creditcardstudentweb.entity.TransactionEntity;
import org.springframework.stereotype.Component;


@Component
public class TransactionMapper implements Mapper<TransactionDTO, TransactionEntity> {

    @Override
    public TransactionEntity convertDTOToEntity(TransactionDTO dto) {
        return TransactionEntity.builder()
                .id(dto.getId())
                .date(dto.getDate())
                .purchaseDescription(dto.getPurchaseDescription())
                .value(dto.getValue())
                .build();
    }

    public TransactionEntity convertDTOToEntity(TransactionDTO dto, StudentEntity student) {
        var transaction = convertDTOToEntity(dto);
        transaction.setStudent(student);
        return transaction;
    }

    @Override
    public TransactionDTO convertEntityToDTO(TransactionEntity entity) {
        return TransactionDTO.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .purchaseDescription(entity.getPurchaseDescription())
                .value(entity.getValue())
                .studentId(entity.getStudent().getId())
                .build();
    }
}
