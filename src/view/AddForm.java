package view;

import entities.Student;
import utils.MyLogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class AddForm extends JFrame {
    public JPanel PanelWrapper;
    private JPanel Content;
    private JTextField textFieldName;
    private JLabel labelName;
    private JLabel labelAge;
    private JLabel labelId;
    private JCheckBox checkBoxIsCulture;
    private JSpinner spinnerAge;
    private JSpinner spinnerId;
    private JButton buttonAdd;
    private static final Logger logger = MyLogger.getLogger();
    public AddForm(MainForm mainForm) {
        logger.info("AddForm created");
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Получаем данные о студенте из формы
                int id = (int) spinnerId.getValue();
                String name = textFieldName.getText();
                int age = (int) spinnerAge.getValue();
                boolean isCulture = checkBoxIsCulture.isSelected();

                if (name.isEmpty()) {
                    logger.warning("Please enter student name.");
                    JOptionPane.showMessageDialog(mainForm, "Please enter student name.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Создаем нового студента
                Student student = new Student(id, age, name, isCulture);

                // Добавляем студента в группу
                boolean result = mainForm.getStudentGroup().addStudent(student);
                if (result) {
                    mainForm.refreshStudents();
                    spinnerId.setValue(0);
                    spinnerAge.setValue(0);
                    textFieldName.setText("");
                    checkBoxIsCulture.setSelected(false);
                    logger.info("Student added successfully.");
                    JOptionPane.showMessageDialog(mainForm, "Student added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    logger.warning("This id is already exist in collection.");
                    JOptionPane.showMessageDialog(mainForm, "This id is already exist in collection.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }
}
