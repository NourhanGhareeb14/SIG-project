package com.main;

import java.util.ArrayList;
import java.util.Date;


public class Invoice {

    private int invoiceNumber;
    private Date invoiceDate;
    private String customerName;
    private double invoiceTotal;
    private ArrayList<InvoiceItem> invoiceItems;

    public Invoice(int invoiceNumber, Date invoiceDate, String customerName) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(double setInvoiceTotal) {
        this.invoiceTotal = setInvoiceTotal;
    }

    public ArrayList<InvoiceItem> getInvoiceItems() {
        if (invoiceItems == null) {
            invoiceItems = new ArrayList<>();
        }
        return invoiceItems;
    }

    public void setInvoiceItems(ArrayList<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public void addInvoiceItem(InvoiceItem invoiceItem) {
        if (invoiceItems == null)
            invoiceItems = new ArrayList<>();
        this.invoiceItems.add(invoiceItem);
        this.invoiceTotal += invoiceItem.getItemTotal();
    }
}
