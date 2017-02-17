import java.util.HashSet;
import java.util.Hashtable;

/**
 * A class that represents a faculty member.
 *
 * @author Max Morales
 * @version 4
 * @see User
 */
class Faculty extends User {

    Hashtable<String, Course> coursesTeaching;

    /**
     * The name of this faculty member.
     */
    private String name;

    /**
     * The id of this faculty member.
     */
    private String id;

    /**
     * Returns the name of this faculty member.
     *
     * @return name the name of this faculty member
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this faculty member.
     *
     * @param name this name of this faculty member
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the id number of this faculty member.
     *
     * @return id faculty id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of this faculty member.
     *
     * @param id the id of this faculty member
     */

    void setId(String id) {
        this.id = id;
    }
    
    boolean addCourses(String CRN) {
        if (StudentSystem.courses.containsKey(CRN)) {
            coursesTeaching.put(CRN, StudentSystem.courses.get(CRN));
            return true;
        }
        return false;
    }


    void gradeCourse(String courseToGradeCRN){
    	Course courseToGrade = StudentSystem.courses.get(courseToGradeCRN);	
        HashSet<Student> studentsTakingCourse = courseToGrade.getStudent();

        for (Student studentToGrade : studentsTakingCourse){
            studentToGrade.setClassGrade(courseToGrade.getCRN(), true);
        }

    }


}
