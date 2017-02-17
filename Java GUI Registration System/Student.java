import java.util.HashSet;
import java.util.Hashtable;

/**
 * A class that represents a student user.
 *
 * @author Max Morales
 * @version 4
 * @see User
 */
class Student extends User {

    /**
     * Stores courses that are added to this student by CRN
     */
    HashSet<String> courses = new HashSet<String>();
    /**
     * The id of this student.
     */
    private String id;
    /**
     * The major of this student.
     */
    private String major;

    private int graduationStatus;

    Student (){
        super();
        graduationStatus = 0;
    }

    /**
     * Returns the id number of this student.
     *
     * @return id the id number of this student
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id number of this student.
     *
     * @param id the id number of this student
     */
    void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the major of this student.
     *
     * @return major the major of this student
     */
    public String getMajor() {
        return major;
    }

    /**
     * Sets the major of this student.
     *
     * @param major the major of this student
     */
    void setMajor(String major) {
        this.major = major;
    }

    /**
     * Adds the course with the given CRN to the student
     * if the course exists in the system.
     *
     * @param CRN the course number
     * @return Boolean Returns false if the CRN does not exist.
     */
    boolean addCourses(String CRN) {
        if (StudentSystem.courses.containsKey(CRN)) {
            courses.add(CRN);
            StudentSystem.courses.get(CRN).addStudent(this);
            return true;
        }
        return false;
    }

    void setClassGrade(String classCRN, boolean passOrFail){
        if (passOrFail){
            graduationStatus++;
        }
        courses.remove(classCRN);
        StudentSystem.courses.get(classCRN).removeStudent(this);
    }

    boolean getGraduationStatus(){

        return graduationStatus >= 20;

    }

    void setGraduationStatus(int graduationStatus){
        this.graduationStatus = graduationStatus;
    }

}
