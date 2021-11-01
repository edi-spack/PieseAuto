import Common.Cart;
import Exceptions.DatabaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private MainForm form;

    public CartUserInterface(MainForm form) {
        this.form = form;
        if (frame != null) {
            frame.dispose();
        }

        frame = new JFrame("Cos de cumparaturi");
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        productsTable.setModel(setMyModel());


        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });


        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myCart.removeFromCart(productsTable.getSelectedRow());
                productsTable.setModel(setMyModel());
            }
        });

        placeOrderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    myCart.completeOrder();
                } catch (DatabaseException ex) {
                    ex.printStackTrace();
                }

                int ans = JOptionPane.showConfirmDialog(null,"Plasati comanda?", "Confirmare comanda",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (ans == 0) {
                    frame.dispose();
                    JOptionPane.showMessageDialog(null, "Comanda inregistrata cu succes", "Confirmare",
                            JOptionPane.INFORMATION_MESSAGE);
                }

                form.fillTable();
            }
        });
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
