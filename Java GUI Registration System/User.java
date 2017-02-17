/**
 * A class that represents a user.
 *
 * @author Raul Hinostroza
 * @version 4
 */
class User {

    /**
     * The name of this user.
     */
    protected String name;

    /**
     * The account type of the user. (Staff, admin, etc.)
     */
    protected int accountType;

    /**
     * The username of this user.
     */
    protected String username;

    /**
     * The password of this user.
     */
    protected String password;

    /**
     * Returns the name of the user.
     *
     * @return name the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user to the given parameter.
     *
     * @param name the name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the user's account type.
     *
     * @return accountType the user's account type.
     */
    int getAccountType() {
        return accountType;
    }

    /**
     * Sets the account type to the given parameter.
     *
     * @param n the user's account type.
     */
    void setAccountType(int n) {
        accountType = n;
    }

    /**
     * Returns the user's username.
     *
     * @return username the user's username.
     */
    String getUsername() {
        return username;
    }

    /**
     * Sets the username of this user to the given parameter.
     *
     * @param username the user's username
     */
    void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the user's password.
     *
     * @return password the user's password
     */
    String getPassword() {
        return password;
    }

    /**
     * Sets the password to the given parameter.
     *
     * @param password the user's password
     */
    void setPassword(String password) {
        this.password = password;
    }
}
