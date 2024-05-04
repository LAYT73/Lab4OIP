package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a group of students.
 *
 * <p>This class maintains a list of Student objects.</p>
 */
public class StudentGroup implements IStudentGroup {
    private List<Student> students;

    /**
     * Constructs a new StudentGroup with an empty list of students.
     */
    public StudentGroup() {
        students = new ArrayList<>();
    }

    /**
     * Adds a student to the group.
     *
     * @param student The student to add.
     */
    public boolean addStudent(Student student) {
        // Проверяем, нет ли уже студента с таким же id
        for (Student existingStudent : students) {
            if (existingStudent.getId() == student.getId()) {
                // Если студент с таким id уже есть, не добавляем его
                return false;
            }
        }

        // Если студента с таким id еще нет, добавляем его в список
        students.add(student);
        return true;
    }

    /**
     * Removes a student from the group.
     *
     * @param student The student to remove.
     */
    public void removeStudent(Student student) {
        students.remove(student);
    }

    /**
     * Gets a student by ID.
     *
     * @param id The ID of the student to find.
     * @return The student with the specified ID, or null if not found.
     */
    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null; // Студент с таким ID не найден
    }

    /**
     * Gets the list of students in the group.
     *
     * @return The list of students.
     */
    public List<Student> getStudents() {
        return students;
    }
}
