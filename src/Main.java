import view.MainForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(false);
        JFrame frame = new JFrame("Список студентов");
        frame.setContentPane(new MainForm().PanelWrapper);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(500, 200);
        frame.pack();
        frame.setSize(1000, 500);
        frame.setVisible(true);
    }
}