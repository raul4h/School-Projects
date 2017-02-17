/**
 * A class that represents a staff member.
 *
 * @author Raul Hinostroza
 * @version 4
 * @see User
 */
class Staff extends User {

    /**
     * The name of the staff member.
     */
    private String name;

    /**
     * Returns the name of this staff member.
     *
     * @return name the name of this staff member
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this staff member.
     *
     * @param name the name of this staff member
     */
    public void setName(String name) {
        this.name = name;
    }
}
