package com.fiap.onescjr.creditcardstudentweb.service;

import com.fiap.onescjr.creditcardstudentweb.dto.ReportDTO;
import com.fiap.onescjr.creditcardstudentweb.dto.StudentDTO;
import com.fiap.onescjr.creditcardstudentweb.dto.TransactionDTO;
import com.itextpdf.text.DocumentException;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface ExtractPDFService {

    byte[] createExtractPDF(ReportDTO reportDTO) throws DocumentException;

    byte[] extractPDF (Long studentid, LocalDateTime initial, LocalDateTime end) throws DocumentException;

}
