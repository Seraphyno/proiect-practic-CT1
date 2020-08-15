package com.sda.proiect.utils.mappers;

import com.sda.proiect.models.Invoice;
import com.sda.proiect.models.InvoiceInput;
import com.sda.proiect.models.ItemType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InputToDatabaseObjectTest {

    @Test
    public void testMap() {
        // Given
        // Am creat datele de input pentru metoda pe care vreau s-o testez
        InvoiceInput input = new InvoiceInput(10, ItemType.FILTRU_POLEN);

        // When
        // Am apelat metoda pe care vreau s-o testez si salvez raspunsul acesteia
        Invoice result = InputToDatabaseObject.map(input);

        // Then
        assertEquals(ItemType.FILTRU_POLEN.getPrettyName(), result.getName());
        assertEquals((Integer) 10, result.getQuantity());

        Double expectedTotalPrice = input.getQuantity() * ItemType.FILTRU_POLEN.getUnitPrice();
        assertEquals(expectedTotalPrice, result.getTotalPrice());
        assertNotNull(result.getCreated());
    }
}