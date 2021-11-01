import Common.Cart;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class CartUserInterface {
    private JPanel rootPanel;
    private JButton placeOrderBtn;
    private JButton backBtn;
    private JScrollPane scrollPane;
    private JButton deleteBtn;
    private JTable productsTable;
    private static JFrame frame;
    private static Cart myCart = Cart.getInstance();

    public CartUserInterface() {
        if (frame != null) {
            frame.dispose();
        }

        frame = new JFrame("Cos de cumparaturi");
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        productsTable.setModel(setMyModel());


    }

    private DefaultTableModel setMyModel() {

        DefaultTableModel myModel = new DefaultTableModel() {
            final String[] columnNames = {"Denumire piesa", "Marca", "Model", "Pret"};

            @Override
            public int getColumnCount() {
                return columnNames.length;
            }

            @Override
            public String getColumnName(int index) {
                return columnNames[index];
            }
        };

        ArrayList<String[]> items = myCart.getCartItems();

        for (String[] item: items) {
            myModel.addRow(item);
        }

        return myModel;
    }

}
