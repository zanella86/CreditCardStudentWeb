package com.fiap.onescjr.creditcardstudentweb.extract;

import com.itextpdf.text.DocumentException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class ExtractController {
    @GetMapping("/extrato")
    public ResponseEntity<ByteArrayResource> extractPDF(@RequestParam("student") String students) {
        try {
            // cria o PDF usando a classe ExtractPDF
            byte[] pdfContents = ExtractPDF.createExtractPDF(Collections.singletonList(students));

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