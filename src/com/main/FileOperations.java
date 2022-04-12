 package com.main;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileOperations {
    static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");

    public static void loadFile(ArrayList<Invoice> invoicesData, Component component) throws Exception {
        invoicesData.clear();
        JOptionPane.showMessageDialog(component, "Select invoices file ", "Invoices", JOptionPane.WARNING_MESSAGE);
        JFileChooser file = new JFileChooser();
        int option = file.showOpenDialog(component);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selected = file.getSelectedFile();
            FileReader fileReader = new FileReader(selected);
            BufferedReader buffer = new BufferedReader(fileReader);
            String invoiceString = null;
            while ((invoiceString = buffer.readLine()) != null) {
                String[] items = invoiceString.split(",");
                String invoiceNumberStr = items[0];
                String invoiceDateStr = items[1];
                String customerName = items[2];

                int invoiceNumber = Integer.parseInt(invoiceNumberStr);
                Date invoiceDate = dateFormat.parse(invoiceDateStr);
                Invoice invoice = new Invoice(invoiceNumber, invoiceDate, customerName);
                invoicesData.add(invoice);
            }
            buffer.close();
            fileReader.close();

            JOptionPane.showMessageDialog(component, "Select invoice items", "Invoice item", JOptionPane.WARNING_MESSAGE);
            file = new JFileChooser();
            option = file.showOpenDialog(component);
            if (option == JFileChooser.APPROVE_OPTION) {
                selected = file.getSelectedFile();
                fileReader = new FileReader(selected);
                buffer = new BufferedReader(fileReader);
                while ((invoiceString = buffer.readLine()) != null) {
                    String[] invoiceItems = invoiceString.split(",");
                    String invoiceNumberStr = invoiceItems[0];
                    String item = invoiceItems[1];
                    String invoicePriceStr = invoiceItems[2];
                    String invoiceCountStr = invoiceItems[3];

                    int invoiceNumber = Integer.parseInt(invoiceNumberStr);
                    double price = Double.parseDouble(invoicePriceStr);
                    int count = Integer.parseInt(invoiceCountStr);
                    Invoice invoice = findInvoiceByNumber(invoicesData, invoiceNumber);
                    InvoiceItem invoiceItem = new InvoiceItem(item, price, count, invoice);
                    invoice.addInvoiceItem(invoiceItem);
                }
            }
            buffer.close();
            fileReader.close();
        }
    }

    private static Invoice findInvoiceByNumber(ArrayList<Invoice> invoicesData, int invoiceNumber) {
        for (Invoice invoice : invoicesData) {
            if (invoice.getInvoiceNumber() == invoiceNumber) {
                return invoice;
            }
        }
        return null;
    }

    public static void saveFile(ArrayList<Invoice> invoicesData, Component component, JTable invoiceTable) throws IOException {
        JOptionPane.showMessageDialog(component, "Save invoices file.", "Invoice file", JOptionPane.INFORMATION_MESSAGE);
        JFileChooser fs = new JFileChooser();
        fs.setDialogTitle("Choose File Save");
        int userSelection = fs.showSaveDialog(component);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fs.getSelectedFile();
            FileWriter fw = new FileWriter(fileToSave + ".csv");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < invoiceTable.getRowCount(); i++) {
                for (int j = 0; j < invoiceTable.getColumnCount(); j++) {

                    bw.write(invoiceTable.getValueAt(i, j).toString() + ",");
                }
                bw.newLine();
            }
            JOptionPane.showMessageDialog(component, "Saved Successfully ", "Save Message", JOptionPane.INFORMATION_MESSAGE);
            bw.close();
            fw.close();

            JOptionPane.showMessageDialog(component, "Save invoice items file ", "Invoice items", JOptionPane.INFORMATION_MESSAGE);
            fs = new JFileChooser();
            fs.setDialogTitle("Choose File Save");
            userSelection = fs.showSaveDialog(component);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                fileToSave = fs.getSelectedFile();
                fw = new FileWriter(fileToSave + ".csv");
                bw = new BufferedWriter(fw);
                for (Invoice inv : invoicesData) {
                    for (InvoiceItem item : inv.getInvoiceItems()) {
                        bw.write(inv.getInvoiceNumber() + "," + item.getItemName() + "," + item.getItemPrice() + "," + item.getItemCount());
                        bw.newLine();
                    }

                }
                JOptionPane.showMessageDialog(component, "Saved Successfully.", "Save Message", JOptionPane.INFORMATION_MESSAGE);
                bw.close();
                fw.close();

            }


        }
    }

}
