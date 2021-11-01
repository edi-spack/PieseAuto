import javax.swing.*;
import Exceptions.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Login extends JDialog {
    private JPanel contentPane;
    private JButton buttonLogin;
    private JTextField usernameTextField;
    private JPasswordField passwordField;

    public Login() {
        setTitle("Login");
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonLogin);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        //setLocationRelativeTo(null);

        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    checkUser();
                } catch (UnknownUser | DatabaseException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    public void checkUser() throws UnknownUser, DatabaseException {
        ArrayList<String[]> credentials = new ArrayList<>();
        String[] firstUser = {"user01", "1234"};
        credentials.add(firstUser);

        for (String[] credential : credentials) {
            if (!usernameTextField.getText().equals(credential[0]) &&
                    !passwordField.getPassword().toString().equals(credential[1])) {
                throw new UnknownUser();
            }
            else {
                new MainForm();
                dispose();
                break;
            }
        }
    }
}
