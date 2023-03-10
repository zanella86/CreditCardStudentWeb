package com.fiap.onescjr.creditcardstudentweb.repository;

import com.fiap.onescjr.creditcardstudentweb.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
