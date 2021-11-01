import Common.AutoPart;
import Database.SQLiteInterface;
import Exceptions.DatabaseException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class MainForm {
    private JTextField searchTextField;
    private JPanel rootPanel;
    private JButton addButton;
    private JTable table;
    private TableRowSorter sorter;
    private JButton cartButton;
    private JFrame frame;
    private ArrayList<AutoPart> parts;

    public MainForm() throws DatabaseException {
        frame = new JFrame("Piese Auto");
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        parts = new ArrayList<>();
        DefaultTableModel model = new DefaultTableModel() {
            final String[] columnNames = {"Id", "Name", "Brand", "Model", "Price", "Stock"};

            @Override
            public int getColumnCount() {
                return columnNames.length;
            }

            @Override
            public String getColumnName(int index) {
                return columnNames[index];
            }
        };

        sorter = new TableRowSorter(model);
        table.setModel(model);
        table.setRowSorter(sorter);

        //addRowToTable("name", "brand", "model", "id", 5.38, 10);

        SQLiteInterface database = new SQLiteInterface();
        parts = database.getDatabase();
        fillTable(parts);

        //search field
        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchTextField.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchTextField.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchTextField.getText());
            }
            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }
        });
    }

    private void addRowToTable(String id, String name, String brand, String model, double price, int stock) {
        ((DefaultTableModel)table.getModel()).addRow(new String[]{id, name, brand, model, Double.toString(price), Integer.toString(stock)});
    }


    private void fillTable(ArrayList<AutoPart> partsList) {
        for (AutoPart part: parts) {
            addRowToTable(part.getId(), part.getName(), part.getBrand(), part.getModel(), part.getPrice(), part.getStock());
        }
    }

    public static void main(String[] args) throws DatabaseException {
        //new Login();
        new MainForm();
    }
}
