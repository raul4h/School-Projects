import java.util.HashSet;

/**
 * A class that represents the courses in the system.
 *
 * @author Andres Cabrera
 * @version 4 (11/12/2016)
 */
class Course {

    /**
     * Unique number that identifies this course.
     */
    private String CRN;

    /**
     * The name of this course
     */
    private String name;

    /**
     * The college this course belongs to
     */
    private String college;

    private HashSet<Student> students;

    /**
     * Constructor of Course
     *
     * @param CRN     course number
     * @param name    the name of the course
     * @param college the college this course belongs to
     */
    Course(String CRN, String name, String college) {
        this.CRN = CRN;
        this.name = name;
        this.college = college;
        students = new HashSet<>();
    }

    /**
     * Returns the CRN of this course.
     *
     * @return CRN course number
     */
    String getCRN() {
        return CRN;
    }

    /**
     * Sets the CRN of this course.
     *
     * @param CRN course number
     */
    public void setCRN(String CRN) {
        this.CRN = CRN;
    }

    /**
     * Returns the name of this course.
     *
     * @return name the name of this course
     */
    String getName() {
        return name;
    }

    /**
     * Sets the name of this course.
     *
     * @param name the name of this course
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the college this course belongs to.
     *
     * @return college the college this course belongs to
     */
    public String getCollege() {
        return college;
    }

    /**
     * Sets the college this course belongs to.
     *
     * @param college the college this course belongs to
     */
    public void setCollege(String college) {
        this.college = college;
    }

    public void addStudent(Student studentToAdd) {
        students.add(studentToAdd);
    }

    public HashSet<Student> getStudent() {
        return students;
    }
    
    public void removeStudent(Student studentToRemove){
    	students.remove(studentToRemove);
    }

}
