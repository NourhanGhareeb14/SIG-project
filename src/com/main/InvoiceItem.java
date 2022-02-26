package com.main;

public class InvoiceItem {

    private String itemName;
    private double itemPrice;
    private int itemCount;
    private double itemTotal;
    private Invoice invoice;

    public InvoiceItem(String itemName, double itemPrice, int count, Invoice invoice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = count;
        this.invoice = invoice;
        this.setItemTotal(this.itemCount * this.itemPrice);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int count) {
        this.itemCount = count;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
