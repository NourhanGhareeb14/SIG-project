package Dialogs;

import com.main.SIG;

import javax.swing.*;
import java.awt.*;

public class InvoicesTableDialog extends JDialog {
    private JLabel customerNameLabel;
    private JTextField customerName;
    private JLabel invoiceDateLabel;
    private JTextField invoiceDate;
    private JButton okButton;
    private JButton cancelButton;

    public InvoicesTableDialog(SIG frame) {

        customerNameLabel = new JLabel("Customer Name : ");
        customerName = new JTextField(20);
        invoiceDateLabel = new JLabel("Invoice Date : ");
        invoiceDate = new JTextField(20);
        okButton = new JButton("Ok");
        okButton.setActionCommand("createInvoice");
        okButton.addActionListener(frame);
        cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("cancelInvoice");
        cancelButton.addActionListener(frame);

        setLayout(new GridLayout(3, 2));

        add(invoiceDateLabel);
        add(invoiceDate);
        add(customerNameLabel);
        add(customerName);
        add(okButton);
        add(cancelButton);

        pack();
    }

    public JTextField getCustomerName() {
        return customerName;
    }

    public JTextField getInvoiceDate() {
        return invoiceDate;
    }
}
