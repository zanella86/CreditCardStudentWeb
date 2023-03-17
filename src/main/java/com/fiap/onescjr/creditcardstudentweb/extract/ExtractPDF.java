package com.fiap.onescjr.creditcardstudentweb.extract;

import java.io.ByteArrayOutputStream;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ExtractPDF {

    public static byte[] createExtractPDF(List<String> student) throws DocumentException {
        // cria um objeto Document para adicionar conteúdo ao PDF
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // cria um objeto PdfWriter para gravar o PDF no ByteArrayOutputStream
        PdfWriter.getInstance(document, byteArrayOutputStream);

        // abre o documento
        document.open();

        // adiciona um parágrafo com o título do extrato
        Paragraph title = new Paragraph("Extrato de Transações\n\n", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // adiciona uma tabela com as informações de transações dos alunos
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(20f);

        // cria as células do cabeçalho da tabela
        PdfPCell alunoHeader = new PdfPCell(new Paragraph("Aluno", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
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
        for (String aluno : student) {
            // adiciona uma linha com as informações de transação do aluno
            PdfPCell alunoCell = new PdfPCell(new Paragraph(aluno, new Font(Font.FontFamily.HELVETICA, 10)));
            alunoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell dataCell = new PdfPCell(new Paragraph("01/01/2022", new Font(Font.FontFamily.HELVETICA, 10)));
            dataCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell valorCell = new PdfPCell(new Paragraph("R$ 10,00", new Font(Font.FontFamily.HELVETICA, 10)));
            valorCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

            // adiciona as células à linha
            table.addCell(alunoCell);
            table.addCell(dataCell);
            table.addCell(valorCell);
        }

        // adiciona a tabela ao documento
        document.add(table);

        // fecha o documento
        document.close();

        // retorna os bytes do PDF
        return byteArrayOutputStream.toByteArray();
    }
}