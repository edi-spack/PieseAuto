import javax.swing.*;
import java.awt.event.*;

public class AddPart extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField brandTextField;
    private JTextField modelTextField;
    private JTextField priceTextField;
    private JTextField stockTextField;

    private MainForm form;

    public AddPart(MainForm form) {
        this.form = form;
        setTitle("Add Part");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setLocationRelativeTo(null);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        pack();
        setVisible(true);
    }

    private void onOK() {
        String id = idTextField.getText();
        String name = nameTextField.getText();
        String brand = brandTextField.getText();
        String model = modelTextField.getText();
        String price = priceTextField.getText();
        String stock = stockTextField.getText();

        if(!id.equals("") && !name.equals("") && !brand.equals("") && !model.equals("") && !price.equals("") && !stock.equals("")) {
            try {
                form.addPart(id, name, brand, model, Double.parseDouble(price), Integer.parseInt(stock));
                dispose();
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Datele introduse nu sunt permise.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Toate campurile trebuie completate.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        dispose();
    }
}
