package com.sda.proiect.models;

public enum ItemType {

    FILTRU_POLEN("Filtru Polen MANN", 123.3),
    ULEI_MOBIL_5W40_5L("Mobil X1 5W40 5L", 100.0);

    private final String prettyName;
    private final Double unitPrice;

    ItemType(String prettyName, Double unitPrice) {
        this.prettyName = prettyName;
        this.unitPrice = unitPrice;
    }

    public String getPrettyName() {
        return prettyName;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }
}
