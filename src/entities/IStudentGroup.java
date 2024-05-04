package entities;

import java.util.List;

/**
 * Interface representing a group of students.
 *
 * <p>This interface provides methods for managing a group of students.</p>
 */
public interface IStudentGroup {

    /**
     * Adds a student to the group.
     *
     * @param student The student to add.
     */
    boolean addStudent(Student student);

    /**
     * Removes a student from the group.
     *
     * @param student The student to remove.
     */
    void removeStudent(Student student);

    /**
     * Gets the list of students in the group.
     *
     * @return The list of students.
     */
    List<Student> getStudents();

    /**
     * Gets a student by ID.
     *
     * @param id The ID of the student to find.
     * @return The student with the specified ID, or null if not found.
     */
    Student findStudentById(int id);
}
