package com.IntuitiveCare.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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

    }
}
