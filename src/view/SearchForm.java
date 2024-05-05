package view;

import entities.Student;
import utils.MyLogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Represents the search form to find a student by ID.
 */
public class SearchForm {
    /** Wrapper panel for the search form. */
    public JPanel PanelWrapper;
    private JPanel Content;
    private JSpinner spinnerId;
    private JButton buttonSearch;
    private static final Logger logger = MyLogger.getLogger();
    private MainForm mainForm; // Reference to the main form

    /**
     * Constructs a new SearchForm instance.
     * @param mainForm Reference to the main form
     */
    public SearchForm(MainForm mainForm) {
        this.mainForm = mainForm;
        logger.info("SearchForm created");

        // Add action listener to the search button
        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });
    }

    /**
     * Searches for a student by ID.
     */
    private void searchStudent() {
        int id = (int) spinnerId.getValue();
        List<Student> students = mainForm._studentGroup.getStudents();

        // Use Stream API to find the student by ID
        Student foundStudent = students.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElse(null);

        if (foundStudent != null) {
            // Student found
            List<Student> result = new ArrayList<>(); // Create a new list to store the found student
            result.add(foundStudent);
            mainForm.displayFilteredStudents(result); // Display the found student
        } else {
            // Student with the specified ID not found
            JOptionPane.showMessageDialog(null, "Student with ID " + id + " not found.");
        }
    }
}
