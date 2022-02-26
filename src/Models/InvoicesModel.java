package Models;

import com.main.Invoice;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class InvoicesModel extends DefaultTableModel {
    private final String[] columnsHeader = {"No.", "Date", "Customer", "Total"};
    private ArrayList<Invoice> invoices;

    public InvoicesModel(ArrayList<Invoice> invoices) {
        this.invoices = invoices;
    }

    public int getColumnCount() {
        return columnsHeader.length;
    }

    public String getColumnName(int column) {
        return columnsHeader[column];
    }

    public ArrayList<Invoice> getInvoices() {
        return invoices;
    }

    public int getRowCount() {
        if (this.invoices == null) {
            invoices = new ArrayList<>();
        }
        return invoices.size();
    }

    @Override
    public Object getValueAt(int row, int column) {
        Invoice invoice = invoices.get(row);
        switch (column) {
            case 0:
                return invoice.getInvoiceNumber();
            case 1:
                return invoice.getInvoiceDate();
            case 2:
                return invoice.getCustomerName();
            case 3:
                return invoice.getInvoiceTotal();
        }
        return null;
    }
}
