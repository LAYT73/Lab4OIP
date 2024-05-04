package entities;

/**
 * Interface representing a student.
 *
 * <p>A student typically has an age, name, and can be marked as cultured.</p>
 */
public interface IStudent {

    /**
     * Gets the id of the student.
     *
     * @return The id of the student.
     */
    int getId();

    /**
     * Sets the id of the student.
     *
     * @param id The new id of the student.
     */
    void setId(int id);

    /**
     * Gets the age of the student.
     *
     * @return The age of the student.
     */
    int getAge();

    /**
     * Sets the age of the student.
     *
     * @param age The new age of the student.
     */
    void setAge(int age);

    /**
     * Gets the name of the student.
     *
     * @return The name of the student.
     */
    String getName();

    /**
     * Sets the name of the student.
     *
     * @param name The new name of the student.
     */
    void setName(String name);

    /**
     * Checks if the student is cultured.
     *
     * @return {@code true} if the student is cultured, {@code false} otherwise.
     */
    boolean isCulture();

    /**
     * Sets the student as cultured.
     *
     * @param isCulture {@code true} if the student is cultured, {@code false} otherwise.
     */
    void setCulture(boolean isCulture);
}


