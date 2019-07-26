package by.taining.cryptomarket.entity;

public class User extends Entity {

    /**
     * The field for storage a userName.
     */
    private  String userName;

    /**
     * The field for storage a name.
     */
    private  String name;

    /**
     * The field for storage a surname.
     */
    private  String surname;

    /**
     * The field for storage a hashOfPassword.
     */
    private   String hashOfPassword;

    /**
     * The field for storage a role.
     */
    private   String role;


    /**
     * The getter for name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * The setter for name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The getter for surname.
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * The setter for surname.
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * The getter for userName.
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * The setter for userName.
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * The getter for hashOfPassword.
     * @return hashOfPassword
     */
    public String getHashOfPassword() {
        return hashOfPassword;
    }

    /**
     * The setter for hashOfPassword.
     * @param hashOfPassword
     */
    public void setHashOfPassword(String hashOfPassword) {
        this.hashOfPassword = hashOfPassword;
    }

    /**
     * The getter for role.
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * The setter for role.
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }
}
