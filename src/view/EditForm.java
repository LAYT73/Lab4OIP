package view;

import entities.Student;
import utils.MyLogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class EditForm extends JFrame {
    public JPanel PanelWrapper;
    private JPanel Content;
    private JSpinner spinnerId;
    private JSpinner spinnerAge;
    private JTextField textFieldName;
    private JCheckBox checkBoxIsCulture;
    private JButton buttonEdit;
    private JLabel labelId;
    private JLabel labelAge;
    private JLabel labelName;
    private MainForm mainForm;
    private static final Logger logger = MyLogger.getLogger();
    public EditForm(MainForm mainForm) {
        this.mainForm = mainForm;
        logger.info("EditForm created");
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });
    }

    // Метод для редактирования студента
    private void editStudent() {
        int id = (int) spinnerId.getValue();
        String name = textFieldName.getText();
        int age = (int) spinnerAge.getValue();
        boolean isCulture = checkBoxIsCulture.isSelected();

        if (name.isEmpty()) {
            logger.warning("Please enter student name.");
            JOptionPane.showMessageDialog(mainForm, "Please enter student name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        logger.info("Started editing a student with Id: " + id);
        // Находим студента по ID в коллекции студентов
        Student studentToUpdate = mainForm.getStudentGroup().findStudentById(id);
        if (studentToUpdate != null) {
            // Обновляем данные студента
            studentToUpdate.setName(name);
            studentToUpdate.setAge(age);
            studentToUpdate.setCulture(isCulture);

            // Обновляем отображение студентов в главной форме
            mainForm.refreshStudents();
            logger.info("Student with ID " + id + " edited succesfully.");
            JOptionPane.showMessageDialog(this, "Student with ID " + id + " edited succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            logger.warning("Student with ID " + id + " not found.");
            JOptionPane.showMessageDialog(this, "Student with ID " + id + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
