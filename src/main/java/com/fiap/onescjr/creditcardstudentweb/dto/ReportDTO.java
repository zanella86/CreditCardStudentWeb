package com.fiap.onescjr.creditcardstudentweb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class ReportDTO implements Serializable {
    private String studentName;
    private List<TransactionDTO> transactions;
}
