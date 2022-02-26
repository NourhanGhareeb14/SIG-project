package Dialogs;

import com.main.SIG;

import javax.swing.*;
import java.awt.*;

public class InvoiceDetailsTableDialog extends JDialog {
    private JLabel itemNameLabel;
    private JTextField itemName;
    private JLabel itemCountLabel;
    private JTextField itemCount;
    private JLabel itemPriceLabel;
    private JTextField itemPrice;
    private JButton okButton;
    private JButton cancelButton;

    public InvoiceDetailsTableDialog(SIG SIG) {

        itemNameLabel = new JLabel("Item Name : ");
        itemName = new JTextField(20);
        itemCountLabel = new JLabel("Item Count : ");
        itemCount = new JTextField(20);
        itemPriceLabel = new JLabel("Item Price : ");
        itemPrice = new JTextField(20);

        okButton = new JButton("Ok");
        okButton.setActionCommand("createItem");
        okButton.addActionListener(SIG);
        cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("CancelItem");
        cancelButton.addActionListener(SIG);

        setLayout(new GridLayout(4,2));

        add(itemNameLabel);
        add(itemName);
        add(itemCountLabel);
        add(itemCount);
        add(itemPriceLabel);
        add(itemPrice);
        add(okButton);
        add(cancelButton);

        pack();
    }
    public JTextField getItemName() {
        return itemName;
    }

    public JTextField getItemCount() {
        return itemCount;
    }

    public JTextField getItemPrice() {
        return itemPrice;
    }
}
