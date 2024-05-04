package view;

import entities.Student;
import utils.MyLogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class RemoveForm extends JFrame {
    public JPanel PanelWrapper;
    private JPanel Content;
    private JSpinner spinnerId;
    private JButton buttonRemove;
    private JLabel labelId;
    private static final Logger logger = MyLogger.getLogger();
    private MainForm mainForm; // Ссылка на главную форму

    public RemoveForm(MainForm mainForm) {
        this.mainForm = mainForm;
        logger.info("RemoveForm created");
        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { removeStudent(); }
        });
    }

    // Метод для удаления студента
    private void removeStudent() {
        int id = (int) spinnerId.getValue();
        logger.info("Started removing a student with Id: " + id);
        // Находим студента по ID в коллекции студентов
        Student studentToRemove = mainForm.getStudentGroup().findStudentById(id);
        if (studentToRemove != null) {
            // Удаляем студента из группы
            mainForm.getStudentGroup().removeStudent(studentToRemove);
            // Обновляем отображение студентов в главной форме
            mainForm.refreshStudents();
            logger.info("Student with ID " + id + " removed succesfully.");
            JOptionPane.showMessageDialog(this, "Student with ID " + id + " removed succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            logger.warning("Student with ID " + id + " not found.");
            JOptionPane.showMessageDialog(this, "Student with ID " + id + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
