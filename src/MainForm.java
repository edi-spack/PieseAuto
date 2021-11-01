import Common.AutoPart;
import Database.SQLiteInterface;
import Exceptions.DatabaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainForm {
    private JTextField searchTextField;
    private JPanel rootPanel;
    private JButton addButton;
    private JTable table;
    private JButton cartButton;
    private JFrame frame;
    private MainForm form;
    private ArrayList<AutoPart> parts;

    public MainForm() {
        form = this;
        frame = new JFrame("Piese Auto");
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        parts = new ArrayList<>();

        table.setModel(new DefaultTableModel() {
            final String[] columnNames = {"Id", "Name", "Brand", "Model", "Price", "Stock"};

            @Override
            public int getColumnCount() {
                return columnNames.length;
            }

            @Override
            public String getColumnName(int index) {
                return columnNames[index];
            }
        });

        fillTable();

        // Listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPart addPartForm = new AddPart(form);
            }
        });
    }

    public void addPart(String id, String name, String brand, String model, double price, int stock) {
        try {
            SQLiteInterface database = new SQLiteInterface();
            database.addPart(id, name, brand, model, price, stock);
            fillTable();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    private void fillTable() {
        SQLiteInterface database = null;

        try {
            database = new SQLiteInterface();
            parts = database.getDatabase();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        ((DefaultTableModel)table.getModel()).setRowCount(0);

        for (AutoPart part: parts) {
            addRowToTable(part.getId(), part.getName(), part.getBrand(), part.getModel(), part.getPrice(), part.getStock());
        }
    }

    private void addRowToTable(String id, String name, String brand, String model, double price, int stock) {
        ((DefaultTableModel)table.getModel()).addRow(new String[]{id, name, brand, model, Double.toString(price), Integer.toString(stock)});
    }

    public static void main(String[] args) throws DatabaseException {
        //new Login();
        new MainForm();
    }
}
