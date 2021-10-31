package Exceptions;

import javax.swing.*;

public class UnknownUser extends Exception {
    public UnknownUser() {
        JOptionPane.showMessageDialog(null, "Numele utilizatorului sau parola incorecta!",
                "Unknown user", JOptionPane.INFORMATION_MESSAGE);
    }
}
