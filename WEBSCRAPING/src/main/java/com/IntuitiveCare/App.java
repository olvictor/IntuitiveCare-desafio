package com.IntuitiveCare;


import com.IntuitiveCare.service.ScrapingService;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) throws IOException {
        String url = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";
        ScrapingService.scraping(url);
    }
}
