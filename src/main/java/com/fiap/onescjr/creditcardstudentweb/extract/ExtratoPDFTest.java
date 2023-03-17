package com.fiap.onescjr.creditcardstudentweb.extract;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExtratoPDFTest {

    public static void main(String[] args) {
        try {
            // Cria uma lista de alunos para testar a geração do PDF
            List<String> alunos = new ArrayList<String>();
            alunos.add("João da Silva");
            alunos.add("Maria da Silva");
            alunos.add("Pedro da Silva");

            // Gera o PDF do extrato de transações para a lista de alunos
            byte[] pdfBytes = ExtractPDF.createExtractPDF(alunos);

            // Salva o PDF em um arquivo para testar o download
            FileOutputStream fos = new FileOutputStream("extrato.pdf");
            fos.write(pdfBytes);
            fos.close();

            System.out.println("PDF gerado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
