package view;

import entities.Student;
import entities.StudentGroup;
import utils.MyLogger;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Main form of the desktop application.
 */
public class MainForm extends JFrame {
    protected StudentGroup _studentGroup = new StudentGroup();
    private DefaultListModel<Student> studentListModel;
    public JPanel PanelWrapper;
    private JPanel PictureBox;
    private JButton buttonEdit;
    private JButton buttonRefresh;
    private JButton buttonRemove;
    private JButton buttonAdd;
    private JButton buttonFilter;
    private JButton buttonSave;
    private JButton buttonSearch;
    private JCheckBox checkBoxTheme;
    private JTextArea textAreaStudents;
    private JTable tableStudents;
    private JTabbedPane tabbedPane1;
    private JPanel statsPanel;
    private JProgressBar progressBarIsCulture;
    private JProgressBar progressBarIsAdults;
    private JLabel labelAmountIsCulture;
    private JLabel labelAmountIsAdults;
    private JLabel labelAmountOfStudents;
    private JLabel labelTitle1;
    private JLabel labelTitle2;
    private JLabel labelTitle3;
    private JScrollPane tableScrollPane;
    private final List<JButton> buttonList;
    private static final Logger logger = MyLogger.getLogger();
    Color themeColor;
    Color fontColor;

    /**
     * Constructs a new MainForm.
     */
    public MainForm() {
        buttonList = Arrays.asList(buttonEdit, buttonRefresh, buttonAdd, buttonRemove, buttonFilter, buttonSave, buttonSearch);
        setStats();
        createTable();
        createList();
        loadStudentsFromFile();
        initializeTable(_studentGroup.getStudents());
        refreshStudents();
        setTheme();
        setButtonListeners();

        logger.info("MainForm created");
    }

    protected void createList() {
        studentListModel = new DefaultListModel<>();
        JList<Student> studentList = new JList<>(studentListModel);
        tabbedPane1.addTab("Список", new JScrollPane(studentList));
        for (Student student : _studentGroup.getStudents()) {
            studentListModel.addElement(student);
        }
    }

    /**
     * Sets action listeners for buttons.
     */
    private void setButtonListeners() {
        checkBoxTheme.addActionListener(e -> setTheme());

        buttonEdit.addActionListener(e -> openEditForm());
        buttonRefresh.addActionListener(e -> refreshStudents());
        buttonRemove.addActionListener(e -> openRemoveForm());
        buttonAdd.addActionListener(e -> openAddForm());
        buttonFilter.addActionListener(e -> filterCulturedStudents());
        buttonSave.addActionListener(e -> saveStudentsToFile());
        buttonSearch.addActionListener(e -> openSearchForm());
    }

    /**
     * Opens the search student form.
     */
    private void openSearchForm() {
        JFrame frame = new JFrame("Редактировать студента");
        frame.setContentPane(new SearchForm(this).PanelWrapper);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(500, 200);
        frame.pack();
        frame.setSize(400, 180);
        frame.setVisible(true);
    }

    /**
     * Opens the edit student form.
     */
    private void openEditForm() {
        JFrame frame = new JFrame("Редактировать студента");
        frame.setContentPane(new EditForm(this).PanelWrapper);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(500, 200);
        frame.pack();
        frame.setSize(400, 180);
        frame.setVisible(true);
    }

    /**
     * Opens the remove student form.
     */
    private void openRemoveForm() {
        JFrame frame = new JFrame("Удалить студента");
        frame.setContentPane(new RemoveForm(this).PanelWrapper);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(500, 200);
        frame.pack();
        frame.setSize(400, 180);
        frame.setVisible(true);
    }

    /**
     * Opens the add student form.
     */
    private void openAddForm() {
        JFrame frame = new JFrame("Добавить студента");
        frame.setContentPane(new AddForm(this).PanelWrapper);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(500, 200);
        frame.pack();
        frame.setSize(400, 180);
        frame.setVisible(true);
    }

    /**
     * Retrieves the student group.
     *
     * @return The student group object
     */
    protected StudentGroup getStudentGroup() {
        return _studentGroup;
    }

    /**
     * Sets the application theme based on the state of the theme checkbox.
     * Updates the colors of UI elements and components accordingly.
     * Logs the theme change operation.
     */
    protected void setTheme() {
        // Log theme initialization
        logger.info("Theme initialization.");

        // Determine theme colors based on the state of the theme checkbox
        if (checkBoxTheme.isSelected()) {
            themeColor = new Color(40, 40, 40);
            fontColor = Color.WHITE;
        } else {
            themeColor = Color.WHITE;
            fontColor = Color.BLACK;
        }

        // Update colors for buttons
        for (JButton button : buttonList) {
            button.setBackground(themeColor);
            button.setForeground(fontColor);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        // Update colors for various UI elements and components
        statsPanel.setBackground(themeColor);
        statsPanel.setForeground(fontColor);
        labelAmountIsAdults.setForeground(fontColor);
        labelAmountOfStudents.setForeground(fontColor);
        labelAmountIsCulture.setForeground(fontColor);
        labelTitle1.setForeground(fontColor);
        labelTitle2.setForeground(fontColor);
        labelTitle3.setForeground(fontColor);
        progressBarIsCulture.setBackground(themeColor);
        progressBarIsAdults.setBackground(themeColor);
        PanelWrapper.setBackground(themeColor);
        PanelWrapper.setForeground(fontColor);
        checkBoxTheme.setBackground(themeColor);
        checkBoxTheme.setForeground(fontColor);
        tabbedPane1.setBackground(themeColor);
        tabbedPane1.setForeground(fontColor);
        textAreaStudents.setBackground(themeColor);
        textAreaStudents.setForeground(fontColor);
        tableStudents.setBackground(themeColor);
        tableStudents.setForeground(fontColor);
        tableStudents.setGridColor(fontColor);
        tableScrollPane.setBackground(themeColor);

        // Log successful theme change
        logger.info("Theme changed successfully.");
    }

    /**
     * Updates statistics related to the student group.
     * Calculates the number of students, cultured students, and adult students.
     * Updates corresponding labels and progress bars with the calculated values.
     */
    protected void setStats() {
        // Get the total number of students
        int studentsLength = _studentGroup.getStudents().size();

        // Calculate the number of cultured students
        int culturedStudentsCount = (int) _studentGroup.getStudents().stream()
                .filter(Student::isCulture)
                .count();

        // Calculate the number of adult students (age >= 18)
        long adultStudentsCount = _studentGroup.getStudents().stream()
                .filter(student -> student.getAge() >= 18)
                .count();

        // Update the labels with the calculated values
        labelAmountIsAdults.setText(String.valueOf(adultStudentsCount));
        labelAmountIsCulture.setText(String.valueOf(culturedStudentsCount));
        labelAmountOfStudents.setText(String.valueOf(studentsLength));

        // Set maximum and minimum values for progress bars
        progressBarIsAdults.setMaximum(studentsLength);
        progressBarIsAdults.setMinimum(0);
        progressBarIsCulture.setMaximum(studentsLength);
        progressBarIsCulture.setMinimum(0);

        // Set values for progress bars
        progressBarIsAdults.setValue((int) adultStudentsCount);
        progressBarIsCulture.setValue(culturedStudentsCount);

        logger.info("Statistics set successfully.");
    }

    /**
     * Refreshes student data.
     * Clears the existing text in the text area, then appends updated student data.
     * Initializes the table with updated student data.
     * Updates statistics related to the student group.
     * Logs the successful refresh of student data.
     */
    protected void refreshStudents() {
        // Retrieve the list of students from the student group
        List<Student> students = _studentGroup.getStudents();

        // Clear existing text in the text area and list model
        textAreaStudents.setText("");
        studentListModel.clear();

        // Append updated student data to the text area
        for (Student student : students) {
            textAreaStudents.append(STR."\{student.toString()}\n");
            studentListModel.addElement(student);
        }

        // Initialize the table with updated student data
        initializeTable(students);

        // Update statistics related to the student group
        setStats();

        // Log the successful refresh of student data
        logger.info("The students have been successfully refreshed.");
    }

    /**
     * Filters and displays students who participate in cultural events.
     * Retrieves students from the student group, filters those who participate in cultural events,
     * and then displays the filtered students.
     * Logs the beginning and successful completion of the filtration process.
     */
    private void filterCulturedStudents() {
        // Log the start of student filtration process
        logger.info("Student filtration has begun.");

        // Retrieve students who participate in cultural events
        List<Student> culturedStudents = _studentGroup.getStudents().stream()
                .filter(Student::isCulture)
                .collect(Collectors.toList());

        // Display filtered students
        displayFilteredStudents(culturedStudents);

        // Log the successful completion of student filtration process
        logger.info("The student filtration has ended successfully.");
    }

    /**
     * Displays filtered student information.
     * Clears the existing text in the text area, then appends filtered student data.
     * Initializes the table with filtered student data.
     * Displays a success message upon successful display.
     *
     * @param students The list of filtered students to display
     */
    protected void displayFilteredStudents(List<Student> students) {
        // Clear existing text in the text area and list model
        textAreaStudents.setText("");
        studentListModel.clear();

        // Append filtered student data to the text area
        for (Student student : students) {
            textAreaStudents.append(STR."\{student.toString()}\n");
            studentListModel.addElement(student);
        }

        // Initialize the table with filtered student data
        initializeTable(students);

        // Log successful display of filtered students
        logger.info("Students filtered by isCulture successfully.");

        // Display success message
        JOptionPane.showMessageDialog(this, "Students filtered by isCulture successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Creates and initializes the table.
     * Adds columns, sets fonts, row heights, and text alignment for better readability.
     * Displays a log message upon successful table creation.
     */
    private void createTable() {
        // Column names for the table
        String[] columnNames = {"ID", "Name", "Age", "isCulture"};
        // Empty row data to start with
        String[][] rowData = {};

        // Create the table with column names and empty row data
        tableStudents = new JTable(rowData, columnNames);

        // Set default header renderer with custom renderer
        tableStudents.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer(this));

        // Create a scroll pane for the table
        tableScrollPane = new JScrollPane(tableStudents);

        // Add the table to the tabbed pane
        tabbedPane1.addTab("Таблица", tableScrollPane);

        // Set font for the table
        tableStudents.setFont(new Font("Arial", Font.PLAIN, 18));

        // Set row height
        tableStudents.setRowHeight(32);

        // Set text alignment for all cells in the table to center
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tableStudents.setDefaultRenderer(Object.class, centerRenderer);

        logger.info("Table created successfully.");
    }

    /**
     * Initializes the table with student data.
     * Adds columns to the table model and populates it with student information.
     * Displays a log message upon successful initialization.
     *
     * @param students The list of students to populate the table with
     */
    private void initializeTable(List<Student> students) {
        DefaultTableModel model = new DefaultTableModel();
        // Add columns to the table model
        model.addColumn("Id");
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("isCulture");

        // Populate the table model with student information
        for (Student student : students) {
            model.addRow(new Object[]{student.getId(), student.getName(), student.getAge(), student.isCulture()});
        }
        // Set the table model
        tableStudents.setModel(model);

        logger.info("Table initialized successfully.");
    }

    /**
     * Saves student data to a file named "students.txt".
     * Writes student information line by line to the file.
     * Displays success or error messages based on the outcome of the operation.
     */
    private void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"))) {
            List<Student> students = _studentGroup.getStudents();
            for (Student student : students) {
                // Write student information to the file
                writer.write(student.getId() + "," + student.getName() + "," + student.getAge() + "," + student.isCulture());
                writer.newLine(); // Move to the next line
            }
            logger.info("Students saved to file students.txt successfully.");
            // Display success message
            JOptionPane.showMessageDialog(this, "Students saved to file successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            logger.warning("Error occurred while saving students to file students.txt.");
            // Display error message
            JOptionPane.showMessageDialog(this, "Error occurred while saving students to file.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    /**
     * Loads student data from a file named "students.txt".
     * Reads the file line by line, parses each line, and creates Student objects.
     * Adds the created students to the student group.
     * Displays success or error messages based on the outcome of the operation.
     * After loading, refreshes the student list in the table.
     */
    private void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Ensure each line contains the correct number of fields
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    boolean isCulture = Boolean.parseBoolean(parts[3]);

                    // Create a new Student object and add it to the student group
                    _studentGroup.addStudent(new Student(id, age, name, isCulture));
                }
            }
            logger.info("Students loaded from file students.txt successfully.");
            // Display success message
            JOptionPane.showMessageDialog(this, "Students loaded from file successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Refresh the student list in the table after loading
            refreshStudents();
        } catch (IOException ex) {
            logger.warning("Error occurred while loading students from file students.txt.");
            // Display error message
            JOptionPane.showMessageDialog(this, "Error occurred while loading students from file.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
