package com.fiap.onescjr.creditcardintegrator.repository;

import com.fiap.onescjr.creditcardintegrator.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query(value = "select te from TransactionEntity te where te.date between :initialDate and :endDate")
    List<TransactionEntity> list(LocalDateTime initialDate, LocalDateTime endDate);

    @Query(value = "select te from TransactionEntity te where te.date between :initialDate and :endDate and te.student.id = :studentId")
    List<TransactionEntity> listByStudent(Long studentId, LocalDateTime initial, LocalDateTime end);
}
