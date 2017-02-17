import java.util.Hashtable;

/**
 * A class that represents the student system.
 *
 * @author Andres Cabrera
 * @version 4
 */
class StudentSystem {

    /**
     * Stores the user accounts according to user name.
     */
    static Hashtable<String, User> accounts = new Hashtable<>();

    /**
     * Stores the courses according to its CRN.
     */
    static Hashtable<String, Course> courses = new Hashtable<>();

    /**
     * Constructor of StudentSystem.
     */
    StudentSystem() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setAccountType(4);
        Course math = new Course("1000", "Mathematics", "College of Science");
        Course computing = new Course("1001", "Computing", "College of Engineering");
        Course english = new Course("1002", "English", "College of Liberal Arts");
        Course history = new Course("1003", "History", "College of Liberal Arts");
        Course biology = new Course("1004", "Biology", "College of Science");
        courses.put(math.getCRN(), math);
        courses.put(computing.getCRN(), computing);
        courses.put(english.getCRN(), english);
        courses.put(history.getCRN(), history);
        courses.put(biology.getCRN(), biology);
        accounts.put(admin.getUsername(), admin);

    }

    /**
     * Initializes the StudentSystem with its default values.
     */
    static void initializeStudentSystem() {

        Course math = new Course("1000", "Mathematics", "College of Science");
        Course computing = new Course("1001", "Computing", "College of Engineering");
        Course english = new Course("1002", "English", "College of Liberal Arts");
        Course history = new Course("1003", "History", "College of Liberal Arts");
        Course biology = new Course("1004", "Biology", "College of Science");
        courses.put(math.getCRN(), math);
        courses.put(computing.getCRN(), computing);
        courses.put(english.getCRN(), english);
        courses.put(history.getCRN(), history);
        courses.put(biology.getCRN(), biology);
        //
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setAccountType(4);
        accounts.put(admin.getUsername(), admin);
        //
        Student defaultStudent = new Student();
        defaultStudent.setName("Jose Cabrera");
        defaultStudent.setUsername("John1");
        defaultStudent.setPassword("123");
        defaultStudent.setId("80539934");
        defaultStudent.setMajor("CS");
        defaultStudent.setAccountType(1);
        defaultStudent.setGraduationStatus(21);
        accounts.put("John1", defaultStudent);
        //
        Student defaultStudent2 = new Student();
        defaultStudent2.setName("Raul Hinostroza");
        defaultStudent2.setUsername("John2");
        defaultStudent2.setPassword("123");
        defaultStudent2.setId("805393");
        defaultStudent2.setMajor("CS");
        defaultStudent2.setAccountType(1);
        accounts.put("John2", defaultStudent2);
        //
        Student defaultStudent3 = new Student();
        defaultStudent3.setName("Max Morales");
        defaultStudent3.setUsername("John3");
        defaultStudent3.setPassword("123");
        defaultStudent3.setId("8053923");
        defaultStudent3.setMajor("CS");
        defaultStudent3.setAccountType(1);
        accounts.put("John3", defaultStudent3);
        defaultStudent.addCourses("1000");
        defaultStudent2.addCourses("1000");
        defaultStudent3.addCourses("1000");
        //
        Faculty defaultFaculty = new Faculty();
        defaultFaculty.setAccountType(2);
        defaultFaculty.setName("Smith");
        defaultFaculty.setUsername("Smith1");
        defaultFaculty.setPassword("123");
        accounts.put(defaultFaculty.getUsername(), defaultFaculty);
        //
        Staff defaultStaff = new Staff();
        defaultStaff.setAccountType(3);
        defaultStaff.setName("Smith");
        defaultStaff.setUsername("Smith2");
        defaultStaff.setPassword("123");
        accounts.put(defaultStaff.getUsername(), defaultStaff);
    }

    /**
     * Logs in the user of a given username if the password is correct.
     *
     * @param username unique name of each user.
     * @param password the password of the user logging in.
     * @return Boolean Returns true if the username exists and the password matches.
     */
    static boolean logIn(String username, String password) {
        if (accounts.containsKey(username)) {
            if (accounts.get(username).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Registers a student into the system.
     *
     * @param name     name of the new student.
     * @param id       id of the new student.
     * @param username new student's username
     * @param password new student's password
     * @param major    new student's major
     * @return Boolean Returns false if the given username already exists
     */
    static boolean registerStudent(String name, String id, String username, String password, String major) {
        Student student = new Student();
        if (!StudentSystem.accounts.containsKey(username)) {
            student.setName(name);
            student.setId(id);
            student.setUsername(username);
            student.setPassword(password);
            student.setMajor(major);
            student.setAccountType(1);

            accounts.put(username, student);
            return true;
        }
        return false;
    }

    /**
     * Registers a staff member into the system.
     *
     * @param name     name of the new staff member
     * @param username new staff member's username
     * @param password new staff member's password
     * @return Boolean Returns false if the username already exists
     */
    static boolean registerStaff(String name, String username, String password) {
        if (!StudentSystem.accounts.containsKey(username)) {
            Staff staff = new Staff();
            staff.setName(name);
            staff.setUsername(username);
            staff.setPassword(password);
            staff.setAccountType(3);

            accounts.put(username, staff);
            return true;
        }
        return false;
    }

    /**
     * Registers a faculty member into the system.
     *
     * @param name     name of the new faculty member
     * @param id       id of the new faculty member
     * @param username new faculty member's username
     * @param password new faculty member's password
     * @return Boolean Returns false if the username already exists
     */
    static boolean registerFaculty(String name, String id, String username, String password) {
        if (!StudentSystem.accounts.containsKey(username)) {
            Faculty faculty = new Faculty();
            faculty.setName(name);
            faculty.setUsername(username);
            faculty.setPassword(password);
            faculty.setId(id);
            faculty.setAccountType(2);

            accounts.put(username, faculty);
            return true;
        }
        return false;
    }

    /**
     * Retrieves the password of the account with the given
     * username.
     *
     * @param username username of user who forgot password
     * @return password the password of the given user
     */
    static String retrievePassword(String username) {
        User lostUser = accounts.get(username);
        return lostUser.getPassword();
    }
}
