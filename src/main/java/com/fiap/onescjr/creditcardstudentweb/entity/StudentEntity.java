package com.fiap.onescjr.creditcardstudentweb.entity;

import com.fiap.onescjr.creditcardstudentweb.config.JpaConfig;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_STUDENT", catalog = JpaConfig.CATALOG)
public class StudentEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String cardCode;

}
