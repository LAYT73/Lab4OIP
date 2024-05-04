package view;
import entities.Student;
import entities.StudentGroup;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main form of the desktop application.
 */
public class MainForm extends JFrame {
    protected StudentGroup _studentGroup = new StudentGroup();
    public JPanel PanelWrapper;
    private JButton buttonEdit;
    private JButton buttonRefresh;
    private JButton buttonRemove;
    private JButton buttonAdd;
    private JButton buttonFilter;
    private JButton buttonSave;
    private JTextArea textAreaStudents;
    private JTable tableStudents;
    private JPanel PictureBox;
    private JTabbedPane tabbedPane1;

    /**
     * Constructs a new MainForm.
     */
    public MainForm() {
        buttonEdit.setName("buttonEdit");
        buttonRefresh.setName("buttonRefresh");
        buttonRemove.setName("buttonRemove");
        buttonAdd.setName("buttonAdd");
        buttonFilter.setName("buttonFilter");
        buttonSave.setName("buttonSave");

        // Add action listeners for buttons
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Редактировать студента");
                frame.setContentPane(new EditForm(MainForm.this).PanelWrapper);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocation(500, 200);
                frame.pack();
                frame.setSize(400, 180);
                frame.setVisible(true);
            }
        });

        buttonRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshStudents();
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Удалить студента");
                frame.setContentPane(new RemoveForm(MainForm.this).PanelWrapper);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocation(500, 200);
                frame.pack();
                frame.setSize(400, 180);
                frame.setVisible(true);
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Добавить студента");
                frame.setContentPane(new AddForm(MainForm.this).PanelWrapper);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocation(500, 200);
                frame.pack();
                frame.setSize(400, 180);
                frame.setVisible(true);
            }
        });

        buttonFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterCulturedStudents();
            }
        });

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveStudentsToFile();
            }
        });

        // Populate StudentGroup with initial students
        loadStudentsFromFile();

        // Display students in JTextArea and JTable
        initializeTable();
        refreshStudents();
    }

    protected StudentGroup getStudentGroup() {
        return _studentGroup;
    }

    // Method to refresh JTextArea with students
    protected void refreshStudents() {
        List<Student> students = _studentGroup.getStudents();
        textAreaStudents.setText(""); // Clear existing text
        for (Student student : students) {
            textAreaStudents.append("Id: "+ student.getId() +", Name: " + student.getName() + ", Age: " + student.getAge() + ", Cultured: " + student.isCulture() + "\n");
        }
        initializeTable();
    }

    // Method to filter students who participate in cultural events
    private void filterCulturedStudents() {
        List<Student> culturedStudents = _studentGroup.getStudents().stream()
                .filter(Student::isCulture) // Filter students who participate in cultural events
                .collect(Collectors.toList());
        displayFilteredStudents(culturedStudents);
    }

    // Method to display filtered students in JTextArea
    private void displayFilteredStudents(List<Student> students) {
        textAreaStudents.setText(""); // Clear existing text
        for (Student student : students) {
            textAreaStudents.append("Id: "+ student.getId() +", Name: " + student.getName() + ", Age: " + student.getAge() + ", Cultured: " + student.isCulture() + "\n");
        }
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("isCulture");
        model.addRow(new Object[]{"Id", "Name", "Age", "isCulture"});

        for (Student student : students) {
            model.addRow(new Object[]{student.getId(), student.getName(), student.getAge(), student.isCulture()});
        }

        tableStudents.setModel(model);
        JOptionPane.showMessageDialog(this, "Students filtered by isCulture successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void initializeTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("isCulture");
        model.addRow(new Object[]{"Id", "Name", "Age", "isCulture"});

        List<Student> students = _studentGroup.getStudents();
        for (Student student : students) {
            model.addRow(new Object[]{student.getId(), student.getName(), student.getAge(), student.isCulture()});
        }

        tableStudents.setModel(model);
    }

    private void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"))) {
            List<Student> students = _studentGroup.getStudents();
            for (Student student : students) {
                writer.write(student.getId() + "," + student.getName() + "," + student.getAge() + "," + student.isCulture());
                writer.newLine(); // Переход на новую строку
            }
            JOptionPane.showMessageDialog(this, "Students saved to file successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred while saving students to file.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    boolean isCulture = Boolean.parseBoolean(parts[3]);

                    // Создаем объект студента и добавляем его в группу
                    _studentGroup.addStudent(new Student(id, age, name, isCulture));
                }
            }
            JOptionPane.showMessageDialog(this, "Students loaded from file successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Обновляем список студентов в таблице после загрузки
            refreshStudents();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred while loading students from file.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
