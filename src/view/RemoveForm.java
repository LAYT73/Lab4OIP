package view;

import entities.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveForm extends JFrame {
    public JPanel PanelWrapper;
    private JPanel Content;
    private JSpinner spinnerId;
    private JButton buttonRemove;
    private JLabel labelId;

    private MainForm mainForm; // Ссылка на главную форму

    public RemoveForm(MainForm mainForm) {
        this.mainForm = mainForm;

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });
    }

    // Метод для удаления студента
    private void removeStudent() {
        int id = (int) spinnerId.getValue();

        // Находим студента по ID в коллекции студентов
        Student studentToRemove = mainForm.getStudentGroup().findStudentById(id);
        if (studentToRemove != null) {
            // Удаляем студента из группы
            mainForm.getStudentGroup().removeStudent(studentToRemove);
            // Обновляем отображение студентов в главной форме
            mainForm.refreshStudents();

            JOptionPane.showMessageDialog(this, "Student with ID " + id + " removed succesfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Student with ID " + id + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
