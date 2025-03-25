package com.IntuitiveCare.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScrapingService {
    private static final String DIRETORIO_DOWN = "downloads/";
    private static final String ARQUIVO_ZIP = "anexos.zip";

    public static void scraping(String url) throws IOException {

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
        }

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
}
