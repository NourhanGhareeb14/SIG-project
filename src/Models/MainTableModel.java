package Models;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MainTableModel extends AbstractTableModel {

    String[] columns;
    private ArrayList<ArrayList<String>> data;

    public MainTableModel(String[] columns, ArrayList<ArrayList<String>> data) {
        this.columns = columns;
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}
