package com.intuitiveCare.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class TransformaDados {
    private static final String DIRETORIO_ZIP_INICIAl = "../WEBSCRAPING/downloads/anexos.zip";
    private static final String ARQUIVOS_EXTRAIDOS = "arquivos_extraidos/";

    public static void main(String[] args) {
        try {
            descompactarZIP(DIRETORIO_ZIP_INICIAl, ARQUIVOS_EXTRAIDOS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void descompactarZIP(String zipFilePath, String outputFolder) throws IOException {
        File diretorioFinal = new File(outputFolder);
        if (!diretorioFinal.exists()) diretorioFinal.mkdirs();

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File newFile = new File(outputFolder + entry.getName());

                try (FileOutputStream fos = new FileOutputStream(newFile)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, bytesRead);
                    }
                }
                zis.closeEntry();
            }
        }
    }
}
