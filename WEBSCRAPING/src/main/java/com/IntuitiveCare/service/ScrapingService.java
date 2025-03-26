package com.IntuitiveCare.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ScrapingService {
    private static final String DIRETORIO_DOWN = "downloads/";
    private static final String ARQUIVOS_ZIP = "anexos.zip";

    public static String scraping(String url) throws IOException {

        try{
            Files.createDirectories(Paths.get(DIRETORIO_DOWN));

            Document documento = Jsoup.connect(url).get();
            Elements links = documento.select("a[href$=.pdf]");

            for (Element link : links) {
                String pdfLink = link.absUrl("href");
                String fileName = link.text().replaceAll("[^a-zA-Z0-9.-]", "_") + "pdf";

                if (fileName.toLowerCase().contains("anexo_i") || fileName.toLowerCase().contains("anexo_ii")) {
                    System.out.println("Baixando: " + pdfLink);
                    baixarArquivos(pdfLink, DIRETORIO_DOWN + fileName);
                }

                ziparArquivos(DIRETORIO_DOWN, DIRETORIO_DOWN + ARQUIVOS_ZIP);
            }
        }catch(Exception erro){
            return "Ocorreu um erro durante a execução do programa:"+ erro.getMessage();

        }
        return "O processo foi concluído com sucesso os arquivos baixados estão na pasta de downloads.";
    }
    private static void baixarArquivos(String arquivoURL, String savePath) throws IOException {
        try (InputStream in = new URL(arquivoURL).openStream();
             FileOutputStream out = new FileOutputStream(savePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

    }

    private  static void ziparArquivos(String sourceDir, String zipFile) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            File dir = new File(sourceDir);
            for (File file : dir.listFiles()) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zos.putNextEntry(zipEntry);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                    zos.closeEntry();
                }
            }
        }
    }
}
