package view;

import entities.Student;
import utils.MyLogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

/**
 * Represents the form for adding a new student.
 */
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

    /**
     * Constructs the AddForm.
     *
     * @param mainForm The MainForm instance
     */
    public AddForm(MainForm mainForm) {
        logger.info("AddForm created");

        // Add action listener to the add button
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent(mainForm);
            }
        });
    }

    /**
     * Adds a new student based on the form input.
     *
     * @param mainForm The MainForm instance
     */
    private void addStudent(MainForm mainForm) {
        // Get student data from the form
        int id = (int) spinnerId.getValue();
        String name = textFieldName.getText();
        int age = (int) spinnerAge.getValue();
        boolean isCulture = checkBoxIsCulture.isSelected();

        // Validate student name
        if (name.isEmpty()) {
            logger.warning("Please enter student name.");
            JOptionPane.showMessageDialog(mainForm, "Please enter student name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create a new student object
        Student student = new Student(id, age, name, isCulture);

        // Add the student to the group
        boolean result = mainForm.getStudentGroup().addStudent(student);
        if (result) {
            // Refresh the display of students in the main form
            mainForm.refreshStudents();

            // Clear form fields after successful addition
            spinnerId.setValue(0);
            spinnerAge.setValue(0);
            textFieldName.setText("");
            checkBoxIsCulture.setSelected(false);

            // Log successful student addition
            logger.info("Student added successfully.");
            JOptionPane.showMessageDialog(mainForm, "Student added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Log if student ID already exists in the collection
            logger.warning("This id is already exist in collection.");
            JOptionPane.showMessageDialog(mainForm, "This id is already exist in collection.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
