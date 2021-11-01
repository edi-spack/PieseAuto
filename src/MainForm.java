import Common.AutoPart;
import Common.Cart;
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

public class MainForm {
    private JTextField searchTextField;
    private JPanel rootPanel;
    private JButton addButton;
    private JTable table;
    private TableRowSorter sorter;
    private JButton cartButton;
    private JButton addToCartBtn;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;
    private JCheckBox checkBox5;
    private JCheckBox checkBox6;
    private JCheckBox checkBox7;
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

        DefaultTableModel tableModel = new DefaultTableModel() {
            final String[] columnNames = {"ID", "Nume", "Marca", "Model", "Pret", "Stoc"};

            @Override
            public int getColumnCount() {
                return columnNames.length;
            }

            @Override
            public String getColumnName(int index) {
                return columnNames[index];
            }
        };

        sorter = new TableRowSorter(tableModel);
        table.setModel(tableModel);
        table.setRowSorter(sorter);

        fillTable();
        searchParts();

        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CartUserInterface();
            }
        });

        addToCartBtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               StringBuffer sb = new StringBuffer();

               String id = tableModel.getValueAt(table.getSelectedRow(), 0).toString();
               String name = tableModel.getValueAt(table.getSelectedRow(), 1).toString();
               String brand = tableModel.getValueAt(table.getSelectedRow(), 2).toString();
               String model = tableModel.getValueAt(table.getSelectedRow(), 3).toString();
               double price = Double.parseDouble(tableModel.getValueAt(table.getSelectedRow(), 4).toString());
               int stock = Integer.parseInt(tableModel.getValueAt(table.getSelectedRow(), 5).toString());

               Cart myCart = Cart.getInstance();
               myCart.addToCart(new AutoPart(id, name, brand, model, price, stock));
           }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPart addPartForm = new AddPart(form);
            }
        });

        checkBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTable();
            }
        });

        checkBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTable();
            }
        });

        checkBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTable();
            }
        });

        checkBox4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTable();
            }
        });

        checkBox5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTable();
            }
        });

        checkBox6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTable();
            }
        });

        checkBox7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTable();
            }
        });
    }

    private void filterTable() {
        SQLiteInterface database = null;

        try {
            database = new SQLiteInterface();
            parts = database.getDatabase();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        ((DefaultTableModel)table.getModel()).setRowCount(0);

        for (AutoPart part: parts) {
            if(checkBox7.isSelected()) {
                if(part.getStock() > 0) {
                    if (checkBox1.isSelected()) {
                        if (part.getPrice() >= 0 && part.getPrice() <= 50) {
                            addRowToTable(part.getId(), part.getName(), part.getBrand(), part.getModel(), part.getPrice(), part.getStock());
                        }
                    }
                    if (checkBox2.isSelected()) {
                        if (part.getPrice() >= 50 && part.getPrice() <= 100) {
                            addRowToTable(part.getId(), part.getName(), part.getBrand(), part.getModel(), part.getPrice(), part.getStock());
                        }
                    }
                    if (checkBox3.isSelected()) {
                        if (part.getPrice() >= 100 && part.getPrice() <= 200) {
                            addRowToTable(part.getId(), part.getName(), part.getBrand(), part.getModel(), part.getPrice(), part.getStock());
                        }
                    }
                    if (checkBox4.isSelected()) {
                        if (part.getPrice() >= 200 && part.getPrice() <= 500) {
                            addRowToTable(part.getId(), part.getName(), part.getBrand(), part.getModel(), part.getPrice(), part.getStock());
                        }
                    }
                    if (checkBox5.isSelected()) {
                        if (part.getPrice() >= 500 && part.getPrice() <= 1000) {
                            addRowToTable(part.getId(), part.getName(), part.getBrand(), part.getModel(), part.getPrice(), part.getStock());
                        }
                    }
                    if (checkBox6.isSelected()) {
                        if (part.getPrice() > 1000) {
                            addRowToTable(part.getId(), part.getName(), part.getBrand(), part.getModel(), part.getPrice(), part.getStock());
                        }
                    }
                }
            } else {
                if (checkBox1.isSelected()) {
                    if (part.getPrice() >= 0 && part.getPrice() <= 50) {
                        addRowToTable(part.getId(), part.getName(), part.getBrand(), part.getModel(), part.getPrice(), part.getStock());
                    }
                }
                if (checkBox2.isSelected()) {
                    if (part.getPrice() >= 50 && part.getPrice() <= 100) {
                        addRowToTable(part.getId(), part.getName(), part.getBrand(), part.getModel(), part.getPrice(), part.getStock());
                    }
                }
                if (checkBox3.isSelected()) {
                    if (part.getPrice() >= 100 && part.getPrice() <= 200) {
                        addRowToTable(part.getId(), part.getName(), part.getBrand(), part.getModel(), part.getPrice(), part.getStock());
                    }
                }
                if (checkBox4.isSelected()) {
                    if (part.getPrice() >= 200 && part.getPrice() <= 500) {
                        addRowToTable(part.getId(), part.getName(), part.getBrand(), part.getModel(), part.getPrice(), part.getStock());
                    }
                }
                if (checkBox5.isSelected()) {
                    if (part.getPrice() >= 500 && part.getPrice() <= 1000) {
                        addRowToTable(part.getId(), part.getName(), part.getBrand(), part.getModel(), part.getPrice(), part.getStock());
                    }
                }
                if (checkBox6.isSelected()) {
                    if (part.getPrice() > 1000) {
                        addRowToTable(part.getId(), part.getName(), part.getBrand(), part.getModel(), part.getPrice(), part.getStock());
                    }
                }
            }
        }
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

    private void searchParts() {
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

    public static void main(String[] args) throws DatabaseException {
        //new Login();
        new MainForm();
    }
}
