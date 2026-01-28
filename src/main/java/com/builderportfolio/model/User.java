package com.builderportfolio.model;

/**
 * Represents a user in the system, which can be either a Project Manager or a Builder.
 * <p>
 * Each user has a unique user ID automatically generated based on their role:
 * <ul>
 *     <li>Project Manager IDs start with "M" (e.g., M1, M2)</li>
 *     <li>Builder IDs start with "B" (e.g., B1, B2)</li>
 * </ul>
 * </p>
 * <p>
 * The class stores user details such as name, email, phone number, experience, password, and selected role.
 * </p>
 */
public class User {

    /** Tracks the last assigned Project Manager ID for auto-increment purposes */
    static long managerLastId = 0;

    /** Tracks the last assigned Builder ID for auto-increment purposes */
    static long builderLastId = 0;

    private String userId;
    private String userName;
    private String userEmail;
    private String userPhNumber;
    private int userExperience;
    private String password;
    private int selectedRole; // 1 = Manager, 2 = Builder

    /**
     * Constructs a new User with the specified details.
     * <p>
     * The userId is automatically generated based on the selected role.
     * </p>
     *
     * @param userName       the name of the user
     * @param userEmail      the email of the user
     * @param userPhNumber   the phone number of the user
     * @param userExperience the experience of the user in years
     * @param password       the password for the user account
     * @param selectedRole   the role of the user (1 = Manager, 2 = Builder)
     */
    public User(String userName, String userEmail, String userPhNumber, int userExperience, String password, int selectedRole) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhNumber = userPhNumber;
        this.userExperience = userExperience;
        this.password = password;
        this.selectedRole = selectedRole;

        // Auto-generate userId based on role
        if (selectedRole == 1) {
            String id = "M"; // Project Manager prefix
            this.userId = id + (++managerLastId); // e.g., M1, M2
        } else {
            String id = "B"; // Builder prefix
            this.userId = id + (++builderLastId); // e.g., B1, B2
        }
    }

    /**
     * Returns the unique user ID.
     *
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Returns the user's name.
     *
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Updates the user's name.
     *
     * @param userName the new name to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the user's email.
     *
     * @return the userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Updates the user's email.
     *
     * @param userEmail the new email to set
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Returns the user's phone number.
     *
     * @return the userPhNumber
     */
    public String getUserPhNumber() {
        return userPhNumber;
    }

    /**
     * Updates the user's phone number.
     *
     * @param userPhNumber the new phone number to set
     */
    public void setUserPhNumber(String userPhNumber) {
        this.userPhNumber = userPhNumber;
    }

    /**
     * Returns the user's experience in years.
     *
     * @return the userExperience
     */
    public int getUserExperience() {
        return userExperience;
    }

    /**
     * Updates the user's experience.
     *
     * @param userExperience the new experience to set
     */
    public void setUserExperience(int userExperience) {
        this.userExperience = userExperience;
    }

    /**
     * Returns the user's password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Updates the user's password.
     *
     * @param password the new password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the user's selected role.
     *
     * @return the selectedRole (1 = Manager, 2 = Builder)
     */
    public int getSelectedRole() {
        return selectedRole;
    }

    /**
     * Updates the user's selected role.
     *
     * @param selectedRole the new role to set (1 = Manager, 2 = Builder)
     */
    public void setSelectedRole(int selectedRole) {
        this.selectedRole = selectedRole;
    }

    /**
     * Returns a string representation of the user, including all details.
     *
     * @return string representation of the user
     */
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhNumber='" + userPhNumber + '\'' +
                ", userExperience=" + userExperience +
                ", password='" + password + '\'' +
                ", selectedRole=" + selectedRole +
                '}';
    }
}
