package com.fiap.onescjr.creditcardstudentweb.service.impl;

import com.fiap.onescjr.creditcardstudentweb.dto.ReportDTO;
import com.fiap.onescjr.creditcardstudentweb.dto.TransactionDTO;
import com.fiap.onescjr.creditcardstudentweb.service.ExtractPDFService;
import com.fiap.onescjr.creditcardstudentweb.service.StudentService;
import com.fiap.onescjr.creditcardstudentweb.service.TransactionService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExtractPDFServiceImpl implements ExtractPDFService {

    private StudentService studentService;
    private TransactionService transactionService;

    public ExtractPDFServiceImpl(StudentService studentService, TransactionService transactionService) {
        this.studentService = studentService;
        this.transactionService = transactionService;
    }

    @Override
    public byte[] createExtractPDF(ReportDTO reportDTO) throws DocumentException {

        // cria um objeto Document para adicionar conteúdo ao PDF
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // cria um objeto PdfWriter para gravar o PDF no ByteArrayOutputStream
        PdfWriter.getInstance(document, byteArrayOutputStream);

        // abre o documento
        document.open();

        // adiciona um parágrafo com o título do extrato
        Paragraph title = new Paragraph("Extrato de Transações\n\nNome estudante: "+ reportDTO.getStudentName(), new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        if (!reportDTO.getTransactions().isEmpty()){
        var valueTransactions = reportDTO.getTransactions().stream().map((dto)->dto.getValue()).reduce((total,value)->total.add(value)).get();
        var numTransactions = reportDTO.getTransactions().size();
        // adiciona uma tabela com as informações de transações dos alunos
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(20f);

        // cria as células do cabeçalho da tabela
        PdfPCell alunoHeader = new PdfPCell(new Paragraph("Descrição", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
        alunoHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        alunoHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
        PdfPCell dataHeader = new PdfPCell(new Paragraph("Data", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
        dataHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        dataHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
        PdfPCell valorHeader = new PdfPCell(new Paragraph("Valor", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
        valorHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        valorHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);

        // adiciona as células do cabeçalho à tabela
        table.addCell(alunoHeader);
        table.addCell(dataHeader);
        table.addCell(valorHeader);

        // preenche a tabela com as informações de transações dos alunos
        for (TransactionDTO transaction : reportDTO.getTransactions()) {
            // adiciona uma linha com as informações de transação do aluno
            PdfPCell alunoCell = new PdfPCell(new Paragraph(transaction.getPurchaseDescription(), new Font(Font.FontFamily.HELVETICA, 10)));
            alunoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell dataCell = new PdfPCell(new Paragraph(transaction.getDate().toString(), new Font(Font.FontFamily.HELVETICA, 10)));
            dataCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell valorCell = new PdfPCell(new Paragraph(transaction.getValue().toString(), new Font(Font.FontFamily.HELVETICA, 10)));
            valorCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

            // adiciona as células à linha
            table.addCell(alunoCell);
            table.addCell(dataCell);
            table.addCell(valorCell);
        }
            Paragraph qtdTransactions = new Paragraph("Quantidade de transações: "+ numTransactions, new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL));
            qtdTransactions.setAlignment(Element.ALIGN_CENTER);
            document.add(qtdTransactions);

            // adiciona a tabela ao documento
            document.add(table);

            Paragraph total = new Paragraph("Valor Total: "+ valueTransactions, new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL));
            total.setAlignment(Element.ALIGN_CENTER);
            document.add(total);


        }else {
            Paragraph mensagemSemTransacoes = new Paragraph("Não existem transações do periodo selecionado ");
            mensagemSemTransacoes.setAlignment(Element.ALIGN_CENTER);
            document.add(mensagemSemTransacoes);
        }


        // fecha o documento
        document.close();

        // retorna os bytes do PDF
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public byte[] extractPDF(Long studentId, LocalDateTime initial, LocalDateTime end) throws DocumentException {
        var student = studentService.select(studentId);
        if (Objects.isNull(student) || student.isEmpty()){
            throw new NoSuchElementException("Student not found");
        }
        var transactions = transactionService.list(studentId, initial, end);
        var reportDTO = ReportDTO.builder()
                .studentName(student.get().getName())
                .transactions(Optional.ofNullable(transactions).orElse(new ArrayList<>()))
                .build();
       return createExtractPDF(reportDTO);
    }

}