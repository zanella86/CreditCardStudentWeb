package com.fiap.onescjr.creditcardstudentweb.controller;

import com.fiap.onescjr.creditcardstudentweb.service.ExtractPDFService;
import com.fiap.onescjr.creditcardstudentweb.service.StudentService;
import com.fiap.onescjr.creditcardstudentweb.service.TransactionService;
import com.fiap.onescjr.creditcardstudentweb.service.impl.ExtractPDFServiceImpl;
import com.itextpdf.text.DocumentException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
public class ExtractController {
    private ExtractPDFService extractPDFService;

    public ExtractController(ExtractPDFService extractPDFService) {
        this.extractPDFService = extractPDFService;
    }

    @GetMapping("/extrato")
    public ResponseEntity<ByteArrayResource> extractPDF(@RequestParam("studentId") Long studentId, LocalDateTime initial, LocalDateTime end) {
        try {
            // cria o PDF usando a classe ExtractPDF
            byte[] pdfContents = extractPDFService.extractPDF(studentId, initial, end);

            // cria um objeto ByteArrayResource para envolver os bytes do PDF
            ByteArrayResource resource = new ByteArrayResource(pdfContents);

            // cria os headers da resposta HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=extrato.pdf");
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

            // cria e retorna uma resposta com o PDF como body e os headers configurados
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch (DocumentException e) {
            // caso ocorra um erro ao criar o PDF, retorna uma resposta com status 500 (Internal Server Error)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}