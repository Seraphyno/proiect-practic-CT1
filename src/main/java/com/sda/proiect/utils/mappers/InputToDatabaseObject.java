package com.sda.proiect.utils.mappers;

import com.sda.proiect.models.Invoice;
import com.sda.proiect.models.InvoiceInput;

import java.util.Date;

public class InputToDatabaseObject {

    private InputToDatabaseObject() {
        throw new IllegalAccessError("Utility class. Please do not instantiate!");
    }

    public static Invoice map(InvoiceInput input) {
        Invoice result = new Invoice();

        result.setName(input.getItemType().getPrettyName());
        result.setQuantity(input.getQuantity());
        result.setTotalPrice(computePrice(input));
        result.setCreated(new Date());

        return result;
    }

    private static Double computePrice(InvoiceInput input) {
        return input.getQuantity() * input.getItemType().getUnitPrice();
    }
}
