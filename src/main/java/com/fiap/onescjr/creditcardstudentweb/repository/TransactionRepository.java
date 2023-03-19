package com.fiap.onescjr.creditcardstudentweb.repository;

import com.fiap.onescjr.creditcardstudentweb.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query(value = "select te from TransactionEntity te where te.date between :initialDate and :endDate")
    List<TransactionEntity> list(String initialDate, String endDate);

    @Query(value = "select * from tb_transaction te where te.date between :initial and :end and te.id_student = :studentId", nativeQuery = true)
    List<TransactionEntity> listByStudent(Long studentId, String initial, String end);
}
