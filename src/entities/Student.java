package entities;

/**
 * Represents a student.
 *
 * <p>A student has an age, name, and can be marked as cultured.</p>
 */
public class Student implements IStudent {
    private int id;
    private int age;
    private String name;
    private boolean isCulture;

    /**
     * Constructs a new Student with default values.
     */
    public Student() {
        // Default constructor
    }

    /**
     * Constructs a new Student with the specified age, name, and cultured status.
     *
     * @param age The age of the student.
     * @param name The name of the student.
     * @param isCulture Whether the student is cultured or not.
     */
    public Student(int id, int age, String name, boolean isCulture) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.isCulture = isCulture;
    }

    /**
     * Gets the id of the student.
     *
     * @return The id of the student.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the student.
     *
     * @param id The new id of the student.
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the age of the student.
     *
     * @return The age of the student.
     */
    @Override
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the student.
     *
     * @param age The new age of the student.
     */
    @Override
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the name of the student.
     *
     * @return The name of the student.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the student.
     *
     * @param name The new name of the student.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if the student is cultured.
     *
     * @return {@code true} if the student is cultured, {@code false} otherwise.
     */
    @Override
    public boolean isCulture() {
        return isCulture;
    }

    /**
     * Sets the student as cultured.
     *
     * @param isCulture {@code true} if the student is cultured, {@code false} otherwise.
     */
    @Override
    public void setCulture(boolean isCulture) {
        this.isCulture = isCulture;
    }
}

