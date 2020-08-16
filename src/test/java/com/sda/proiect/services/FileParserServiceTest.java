package com.sda.proiect.services;

import org.junit.Test;

// TODO
public class FileParserServiceTest {


    @Test
    public void readUserFile() {
        String path = FileParserServiceTest.class.getClassLoader().getResource("input.json").getPath();
    }
}