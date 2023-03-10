package com.fiap.onescjr.creditcardstudentweb.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class TransactionDTO {
    private Long id;
    private String purchaseDescription;
    private LocalDateTime date;
    private BigDecimal value;
    private Long studentId;
}
