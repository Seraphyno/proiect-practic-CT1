package com.sda.proiect.services;

import com.sda.proiect.models.InvoiceInput;
import com.sda.proiect.models.ItemType;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileParserServiceTest {

    private FileParserService service = new FileParserService();

    @Test
    public void testPositive() {
        // GIVEN - un path catre fisierul de resurse

        String path = FileParserServiceTest.class
                .getClassLoader()
                .getResource("input.json")
                .getPath();

        assertTrue(path.endsWith("input.json"));

        // WHEN - apelez serviciul
        List<InvoiceInput> invoiceInputs = service.readUserFile(path);

        // THEN - ar trebui sa imi intoarca o lista de obiecte
        assertEquals(1, invoiceInputs.size());
        assertEquals((Integer) 1, invoiceInputs.get(0).getQuantity());
        assertEquals(ItemType.FILTRU_POLEN, invoiceInputs.get(0).getItemType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegative() {
        String path = "C:\\Users\\Desktop\\input.jason";

        service.readUserFile(path);
    }
}