import javax.swing.*;

public class MainForm {
    private JTextField searchTextField;
    private JPanel rootPanel;
    private JButton addButton;
    private JTable table;
    private JButton cartButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Piese Auto");
        frame.setContentPane(new MainForm().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
