package com.main;

import Dialogs.InvoiceDetailsTableDialog;
import Dialogs.InvoicesTableDialog;
import Models.InvoiceDetailsModel;
import Models.InvoicesModel;
import Models.MainTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class SIG extends JFrame implements ActionListener, ListSelectionListener {

    private JTable invoiceDetailsTable;
    private JLabel invoiceNumber;
    public JTextField invoiceDate;
    private JTextField customerName;
    private JLabel invoiceTotal;
    private JTable invoiceTable;
    private final ArrayList<ArrayList<String>> invoices = new ArrayList<>();
    private final ArrayList<ArrayList<String>> invoiceDetails = new ArrayList<>();
    private final ArrayList<Invoice> invoicesData = new ArrayList<>();
    private ArrayList<InvoiceItem> invoiceItems = new ArrayList<>();
    private InvoicesModel invoicesModel;
    private InvoiceDetailsModel invoiceDetailsModel;
    private InvoicesTableDialog invoicesTableDialog;
    private InvoiceDetailsTableDialog invoiceItemDialog;

    public SIG(String dialogTitle) {
        super(dialogTitle);
        initiateMenuBar();

        JPanel main = new JPanel();
        JPanel layoutBottom = new JPanel();
        JPanel invoiceDetailsPanel = new JPanel();
        JPanel layout1 = new JPanel();
        JPanel layout2 = new JPanel();
        JPanel layout3 = new JPanel();
        JPanel layout4 = new JPanel();
        JPanel layout5 = new JPanel();

        main.setLayout(new GridLayout(1, 2));
        layout1.setLayout(new BorderLayout(5, 5));
        layout2.setLayout(new BorderLayout(5, 5));
        invoiceDetailsPanel.setLayout(new GridLayout(1, 2));
        layout3.setLayout(new BoxLayout(layout3, BoxLayout.Y_AXIS));
        layout4.setLayout(new FlowLayout());
        layout5.setLayout(new GridLayout(4,2));

        initiateInvoicePanel(main, layout1);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        initiateInvoiceDetailsPanel(constraints, layout2, layout3, layout5, layoutBottom);
        invoiceDetailsPanel.add(layout2);
        main.add(invoiceDetailsPanel);
        add(main);
        setSize(1000, 700);
        setLocation(250, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initiateMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem load = new JMenuItem("Load File");
        load.setActionCommand("load");
        JMenuItem save = new JMenuItem("Save File");
        save.setActionCommand("save");
        load.addActionListener(this);
        save.addActionListener(this);
        setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(load);
        menu.add(save);
    }

    private void initiateInvoicePanel(JPanel main, JPanel layout1) {
        JPanel invoicePanel = new JPanel();
        invoicePanel.setLayout(new GridLayout(1, 1));
        JLabel invoiceLabel = new JLabel("Invoice Table");
        layout1.add(invoiceLabel, BorderLayout.NORTH);
        MainTableModel TLeft = new MainTableModel(new String[]{"No.", "Date", "Customer", "Total"}, invoices);
        invoiceTable = new JTable(TLeft);
        invoiceTable = new JTable(TLeft);
        invoiceTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        invoiceTable.getSelectionModel().addListSelectionListener(this);
        layout1.add(new JScrollPane(invoiceTable), BorderLayout.CENTER);
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));

        JButton createNewInvoiceButton = new JButton("Create New Invoice");
        createNewInvoiceButton.setActionCommand("create");
        createNewInvoiceButton.addActionListener(this);
        bottom.add(createNewInvoiceButton);
        JButton delete = new JButton("Delete Invoice");
        delete.setActionCommand("delete");
        delete.addActionListener(this);
        bottom.add(delete);

        layout1.add(bottom, BorderLayout.SOUTH);
        invoicePanel.add(layout1);
        main.add(invoicePanel);
    }

    private void initiateInvoiceDetailsPanel(GridBagConstraints constraints, JPanel layout2, JPanel layout3, JPanel layout5, JPanel layoutBottom) {

        constraints.gridx = 0;
        constraints.gridy = 0;
        layout5.add(new JLabel("Invoice Number"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        invoiceNumber = new JLabel("");
        layout5.add(invoiceNumber, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        layout5.add(new JLabel("Invoice Date"), constraints);

        invoiceDate = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        layout5.add(invoiceDate, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        layout5.add(new JLabel("Customer Name"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        customerName = new JTextField(20);
        layout5.add(customerName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        layout5.add(new JLabel("Invoice Total"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        invoiceTotal = new JLabel("");
        layout5.add(invoiceTotal, constraints);

        layout2.add(layout5, BorderLayout.NORTH);

        layout3.add(new JLabel("Invoice Items"));

        MainTableModel TRight = new MainTableModel(new String[]{"No.", "Item Name", "Item Price", "Count", "Item Total"}, invoiceDetails);
        invoiceDetailsTable = new JTable(TRight);
        layout3.add(new JScrollPane(invoiceDetailsTable));
        layout2.add(layout3, BorderLayout.CENTER);

        layoutBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
        JButton Save = new JButton("Create");
        Save.setActionCommand("saveButton");
        Save.addActionListener(this);
        layoutBottom.add(Save);
        JButton cancel = new JButton("Cancel");
        cancel.setActionCommand("cancel");
        cancel.addActionListener(this);
        layoutBottom.add(cancel);
        layout2.add(layoutBottom, BorderLayout.SOUTH);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        invoiceTableRowSelected();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (Objects.equals(e.getActionCommand(), "createInvoice")) {
            createInvoiceAction();
        } else if (Objects.equals(e.getActionCommand(), "cancelInvoice")) {
            createInvoiceCancel();
        } else if (Objects.equals(e.getActionCommand(), "createItem")) {
            createInvoiceItem();
        } else if (Objects.equals(e.getActionCommand(), "CancelItem")) {
            createItemCancel();
        } else if (Objects.equals(e.getActionCommand(), "saveButton")) {
            createItemAction();
        } else if (Objects.equals(e.getActionCommand(), "cancel")) {
            try {
                deleteItem();
            } catch (Exception item) {
                JOptionPane.showMessageDialog(this, "Invoice item cannot be deleted.", "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        } else if (Objects.equals(e.getActionCommand(), "create")) {
            createInvoice();
        } else if (Objects.equals(e.getActionCommand(), "delete")) {
            try {
                deleteInvoice();
            } catch (Exception file) {
                JOptionPane.showMessageDialog(this, "Invoice cannot be deleted.", "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        } else if (Objects.equals(e.getActionCommand(), "load")) {
            try {
                FileOperations.loadFile(invoicesData, this);
                invoicesModel = new InvoicesModel(invoicesData);
                invoiceTable.setModel(invoicesModel);
                invoiceTable.validate();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Cannot read file.", "Error Message", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else if (Objects.equals(e.getActionCommand(), "save")) {
            try {
                FileOperations.saveFile(invoicesData, this, invoiceTable);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void createInvoice() {
        invoicesTableDialog = new InvoicesTableDialog(this);
        invoicesTableDialog.setLocationRelativeTo(null);
        invoicesTableDialog.setTitle("Create New Invoice");
        invoicesTableDialog.setVisible(true);
    }

    private void deleteInvoice() throws Exception {
        invoiceTable.setModel(invoicesModel);
        int selectedInvoice = invoiceTable.getSelectedRow();
        invoicesData.remove(selectedInvoice);
        invoicesModel.fireTableDataChanged();
    }

    private void createItemAction() {
        invoiceItemDialog = new InvoiceDetailsTableDialog(this);
        invoiceItemDialog.setLocationRelativeTo(null);
        invoiceItemDialog.setTitle("Create New Invoice Item");
        invoiceItemDialog.setVisible(true);
    }

    private void deleteItem() throws Exception {
        invoiceDetailsTable.setModel(invoiceDetailsModel);
        int rowSelected = invoiceDetailsTable.getSelectedRow();
        invoiceItems.remove(rowSelected);
        invoiceDetailsModel.fireTableDataChanged();
    }

    private void invoiceTableRowSelected() {
        invoiceTable.setModel(invoicesModel);
        int selectedRow = invoiceTable.getSelectedRow();
        if (selectedRow != -1) {
            Invoice row = invoicesModel.getInvoices().get(selectedRow);
            invoiceNumber.setText(String.valueOf(row.getInvoiceNumber()));
            customerName.setText(row.getCustomerName());
            invoiceDate.setText(row.getInvoiceDate().toString());
            invoiceTotal.setText(String.valueOf(row.getInvoiceTotal()));
            invoiceItems = row.getInvoiceItems();
            invoiceDetailsModel = new InvoiceDetailsModel(invoiceItems);
            invoiceDetailsTable.setModel(invoiceDetailsModel);
            invoiceDetailsModel.fireTableDataChanged();
        }
    }

    private void createInvoiceAction() {
        String customer = invoicesTableDialog.getCustomerName().getText();
        String invDateStr = invoicesTableDialog.getInvoiceDate().getText();
        Date invoiceDate = new Date();
        try {
            invoiceDate = FileOperations.dateFormat.parse(invDateStr);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Data Format issue", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        invoicesTableDialog.setVisible(false);
        int num = getLastNum() + 1;
        Invoice newInvoice = new Invoice(num, invoiceDate, customer);
        invoicesData.add(newInvoice);
        invoicesModel.fireTableDataChanged();
    }

    private void createInvoiceCancel() {
        invoicesTableDialog.setVisible(false);
    }

    private void createInvoiceItem() {
        String itemName = invoiceItemDialog.getItemName().getText();
        String itemCountStr = invoiceItemDialog.getItemCount().getText();
        String itemPriceStr = invoiceItemDialog.getItemPrice().getText();
        invoiceItemDialog.setVisible(false);
        int itemCount = Integer.parseInt(itemCountStr);
        double itemPrice = Double.parseDouble(itemPriceStr);
        Invoice invoice = invoicesData.get(invoiceTable.getSelectedRow());
        InvoiceItem line = new InvoiceItem(itemName, itemPrice, itemCount, invoice);
        invoice.addInvoiceItem(line);
        invoiceDetailsModel.fireTableDataChanged();
        invoicesModel.fireTableDataChanged();
    }

    private void createItemCancel() {
        invoiceItemDialog.setVisible(false);
    }

    private int getLastNum() {
        int num = 0;
        for (Invoice invoice : invoicesData) {
            if (invoice.getInvoiceNumber() > num) {
                num = invoice.getInvoiceNumber();
            }
        }
        return num;
    }
}
