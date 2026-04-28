package com.example.rawmillmanagement.model;

import java.io.FileWriter;
import java.io.IOException;

public class MillUser {

    private static int totalUserCount = 0;

    private String userId;
    private String userName;
    private String role;
    private String department;
    private String phoneNumber;
    private String emailId;
    private String shift;
    private int experienceYears;
    private String loginName;
    private String password;
    private boolean activeStatus;

    public MillUser(String userId, String userName, String role, String department,
                    String phoneNumber, String emailId, String shift,
                    int experienceYears, String loginName, String password) {

        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be empty.");
        }
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be empty.");
        }
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Role cannot be empty.");
        }
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be empty.");
        }
        if (!phoneNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number must contain exactly 10 digits.");
        }
        if (emailId == null || !emailId.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        if (shift == null || shift.trim().isEmpty()) {
            throw new IllegalArgumentException("Shift cannot be empty.");
        }
        if (experienceYears < 0) {
            throw new IllegalArgumentException("Experience cannot be negative.");
        }
        if (loginName == null || loginName.trim().isEmpty()) {
            throw new IllegalArgumentException("Login name cannot be empty.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        this.userId = userId;
        this.userName = userName;
        this.role = role;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.shift = shift;
        this.experienceYears = experienceYears;
        this.loginName = loginName;
        this.password = password;
        this.activeStatus = true;

        totalUserCount++;
        writeUserToFile();
    }

    private void writeUserToFile() {
        try (FileWriter fw = new FileWriter("users.txt", true)) {
            fw.write(userId + "," + userName + "," + role + "," + department + "," +
                    phoneNumber + "," + emailId + "," + shift + "," + experienceYears + "," +
                    loginName + "," + activeStatus + "\n");
        } catch (IOException e) {
            System.out.println("File error while saving user: " + e.getMessage());
        }
    }

    public void displayUser(int index) {
        System.out.println(index + ". " + userName +
                " | Role: " + role +
                " | Dept: " + department +
                " | Shift: " + shift +
                " | Experience: " + experienceYears + " yrs");
    }

    public static int getTotalUserCount() {
        return totalUserCount;
    }
}

