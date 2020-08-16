package com.sda.proiect.models;

public class InvoiceInput {
    private Integer quantity;
    private ItemType itemType;

    public InvoiceInput(Integer quantity, ItemType itemType) {
        this.quantity = quantity;
        this.itemType = itemType;
    }

    public InvoiceInput() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InvoiceInput{");
        sb.append(", quantity=").append(quantity);
        sb.append(", itemType=").append(itemType);
        sb.append('}');
        return sb.toString();
    }
}
