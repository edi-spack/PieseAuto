import Common.AutoPart;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MainForm {
    private JTextField searchTextField;
    private JPanel rootPanel;
    private JButton addButton;
    private JTable table;
    private JButton cartButton;
    private JFrame frame;
    private ArrayList<AutoPart> parts;

    public MainForm() {
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

        //addRowToTable("name", "brand", "model", "id", 5.38, 10);
    }

    private void addRowToTable(String id, String name, String brand, String model, double price, int stock) {
        ((DefaultTableModel)table.getModel()).addRow(new String[]{id, name, brand, model, Double.toString(price), Integer.toString(stock)});
    }

    public static void main(String[] args) {
        //new Login();
        new MainForm();
    }
}
