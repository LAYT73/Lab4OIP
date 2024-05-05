import utils.MyLogger;
import view.MainForm;

import javax.swing.*;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = MyLogger.getLogger();

    public static void main(String[] args) {
        logger.info("Application started.");

        JFrame.setDefaultLookAndFeelDecorated(false);
        JFrame frame = new JFrame("Список студентов");
        frame.setContentPane(new MainForm().PanelWrapper);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(300, 140);
        frame.pack();
        frame.setSize(1400, 800);
        frame.setVisible(true);
    }
}