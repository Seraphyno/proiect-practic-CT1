package com.sda.proiect.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.proiect.models.InvoiceInput;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileParserService {

    private final ObjectMapper objectMapper;

    public FileParserService() {
        this.objectMapper = new ObjectMapper();
    }

    public List<InvoiceInput> readUserFile(String path) {
        // verific daca fisierul este json
        if (!checkJsonType(path)) {
            throw new IllegalArgumentException("The file is not JSON type!");
        }

        try {
            File file = new File(path);

            // Linia initiala, care citeste un singur obiect din fisier, de tip InvoiceInput
//            InvoiceInput invoiceInput = objectMapper.readValue(file, InvoiceInput.class);
            // Daca va jucati cu ea, nu uitati sa schimbati return type de metoda

            // folosim din ObjectMapper metoda care imi genereaza o "fabrica" de tipuri de date (compuse), care mai departe
            // imi va genera un collection type, care primeste 2 parametri: tipul de colectie si tipul de date stocate in colectie
            return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, InvoiceInput.class));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean checkJsonType(String path) {
        return path.toLowerCase().endsWith(".json");
    }
}
