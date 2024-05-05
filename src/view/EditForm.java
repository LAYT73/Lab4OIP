package view;

import entities.Student;
import utils.MyLogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

/**
 * Represents the form for editing student information.
 */
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

    /**
     * Constructs the EditForm.
     *
     * @param mainForm The MainForm instance
     */
    public EditForm(MainForm mainForm) {
        this.mainForm = mainForm;
        logger.info("EditForm created");

        // Add action listener to the edit button
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });
    }

    /**
     * Edits the student information based on user input.
     */
    private void editStudent() {
        // Get input values from the form components
        int id = (int) spinnerId.getValue();
        String name = textFieldName.getText();
        int age = (int) spinnerAge.getValue();
        boolean isCulture = checkBoxIsCulture.isSelected();

        // Validate input
        if (name.isEmpty()) {
            logger.warning("Please enter student name.");
            JOptionPane.showMessageDialog(mainForm, "Please enter student name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Log the start of student editing
        logger.info("Started editing a student with Id: " + id);

        // Find the student to update in the student group
        Student studentToUpdate = mainForm.getStudentGroup().findStudentById(id);
        if (studentToUpdate != null) {
            // Update student data
            studentToUpdate.setName(name);
            studentToUpdate.setAge(age);
            studentToUpdate.setCulture(isCulture);

            // Refresh the display of students in the main form
            mainForm.refreshStudents();

            // Log successful student edit
            logger.info("Student with ID " + id + " edited successfully.");
            JOptionPane.showMessageDialog(this, "Student with ID " + id + " edited successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Log student not found
            logger.warning("Student with ID " + id + " not found.");
            JOptionPane.showMessageDialog(this, "Student with ID " + id + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
