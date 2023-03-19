package com.fiap.onescjr.creditcardstudentweb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
public class StudentDTO implements Serializable {

    private Long id;
    private String name;

}
