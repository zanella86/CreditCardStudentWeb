package com.fiap.onescjr.creditcardintegrator.repository;

import com.fiap.onescjr.creditcardintegrator.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
