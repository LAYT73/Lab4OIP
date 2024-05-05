package view;

import entities.Student;
import utils.MyLogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

/**
 * Represents the form for removing a student.
 */
public class RemoveForm extends JFrame {
    public JPanel PanelWrapper;
    private JPanel Content;
    private JSpinner spinnerId;
    private JButton buttonRemove;
    private JLabel labelId;
    private static final Logger logger = MyLogger.getLogger();
    private MainForm mainForm; // Reference to the main form

    /**
     * Constructs the RemoveForm.
     *
     * @param mainForm The MainForm instance
     */
    public RemoveForm(MainForm mainForm) {
        this.mainForm = mainForm;
        logger.info("RemoveForm created");

        // Add action listener to the remove button
        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });
    }

    /**
     * Removes the selected student.
     */
    private void removeStudent() {
        // Get the student ID to remove
        int id = (int) spinnerId.getValue();
        logger.info("Started removing a student with Id: " + id);

        // Find the student to remove in the student group
        Student studentToRemove = mainForm.getStudentGroup().findStudentById(id);
        if (studentToRemove != null) {
            // Remove the student from the group
            mainForm.getStudentGroup().removeStudent(studentToRemove);

            // Refresh the display of students in the main form
            mainForm.refreshStudents();

            // Log successful student removal
            logger.info("Student with ID " + id + " removed successfully.");
            JOptionPane.showMessageDialog(this, "Student with ID " + id + " removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Log if student with ID not found
            logger.warning("Student with ID " + id + " not found.");
            JOptionPane.showMessageDialog(this, "Student with ID " + id + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
