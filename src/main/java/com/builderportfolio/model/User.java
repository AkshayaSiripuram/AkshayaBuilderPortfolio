package com.builderportfolio.model;

// Represents a user in the system (either Project Manager or Builder)
public class User {
    static long managerLastId = 0; // tracks last assigned manager ID
    static long builderLastId = 0; // tracks last assigned builder ID

    private String userId;
    private String userName;
    private String userEmail;
    private String userPhNumber;
    private int userExperience;
    private String password;
    private int selectedRole; // 1 = Manager, 2 = Builder

    // Constructor initializes user details and auto-generates userId based on role
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

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhNumber() {
        return userPhNumber;
    }

    public void setUserPhNumber(String userPhNumber) {
        this.userPhNumber = userPhNumber;
    }

    public int getUserExperience() {
        return userExperience;
    }

    public void setUserExperience(int userExperience) {
        this.userExperience = userExperience;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(int selectedRole) {
        this.selectedRole = selectedRole;
    }

    // Returns string representation for logging or debugging purposes
    @Override
    public String toString() {
        return "User{" + "userId='" + userId + '\'' + ", userName='" + userName + '\'' + ", userEmail='" + userEmail + '\'' + ", userPhNumber='" + userPhNumber + '\'' + ", userExperience=" + userExperience + ", password='" + password + '\'' + ", selectedRole=" + selectedRole + '}';
    }
}
