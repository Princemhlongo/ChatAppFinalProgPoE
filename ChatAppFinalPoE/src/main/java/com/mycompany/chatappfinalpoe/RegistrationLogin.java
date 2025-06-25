/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatappfinalpoe;

/**
 *
 * @author Nqobile
 */
/**
 * CODE ATTRIBUTION
 *
 * This project was independently created by Princess Nqobile Mhlongo in fulfilment of the Final Programming PoE (2025),
 * developed using Java, NetBeans, and Maven, and incorporating JOptionPane for the user interface.
 * While the majority of this system is original, the following resources were consulted to support design and implementation:
 *
 * Title: Java User Input Tutorial  
 * Author: W3Schools  
 * Date Accessed: 19 April 2025  
 * Available: https://www.w3schools.com/java/java_user_input.asp
 *
 * Title: How to Validate Phone Numbers in Java  
 * Author: Baeldung  
 * Date Accessed: 21 April 2025  
 * Available: https://www.baeldung.com/java-regex-validate-phone-numbers
 *
 * Title: JSON.simple Java JSON Encoding Example  
 * Author: CodeJava.net  
 * Date Accessed: 4 May 2025  
 * Available: https://www.codejava.net/java-se/file-io/write-json-to-file
 *
 * Title: How to Run Unit Tests with JUnit 5  
 * Author: JUnit Official Docs  
 * Date Accessed: 10 May 2025  
 * Available: https://junit.org/junit5/docs/current/user-guide/
 *
 * ChatGPT by OpenAI (2025) was used under active supervision of the student between April–June 2025,  
 * for debugging assistance, code review, enhancement guidance, and test generation. Notable collaborative improvements included:
 * - Refactoring registration logic for username, phone, and password validation (April 2025)
 * - Generating unique Message ID and Hash formats and adjusting search/delete logic (May 2025)
 * - Designing meaningful unit tests to ensure code quality (May 2025)
 * - Maven dependency configuration and JSON implementation with manual output writing (June 2025)
 * - Strengthening error handling, user flow, and UI structure using JOptionPane dialogs (June 2025)
 *
 * All code provided through assistance was adapted to suit the student’s own logic, structure,  
 * and original feature requirements. The student has ensured full understanding of every implemented function.
 */
import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.*;

//Handles registration and login functionality with validation. 
//Enforces strong password, valid phone number, and specific username rules.


public class RegistrationLogin {

    private static ArrayList<User> users = new ArrayList<>();

    public static void registerUser() {
        String firstName = JOptionPane.showInputDialog("Enter your First Name:");
        if (firstName == null || firstName.isBlank()) {
            JOptionPane.showMessageDialog(null, "First Name is required.");
            return;
        }

        String lastName = JOptionPane.showInputDialog("Enter your Last Name:");
        if (lastName == null || lastName.isBlank()) {
            JOptionPane.showMessageDialog(null, "Last Name is required.");
            return;
        }

        String username = JOptionPane.showInputDialog("Enter username (must include _ and max 5 characters):");
        if (username == null || !isValidUsername(username)) {
            JOptionPane.showMessageDialog(null, "Invalid username. It must contain an underscore (_) and be no more than 5 characters long.");
            return;
        } else {
            JOptionPane.showMessageDialog(null, "Username successfully captured.");
        }

        String password = JOptionPane.showInputDialog("Enter password (min 8 chars, 1 uppercase, 1 digit):");
        if (password == null || !isValidPassword(password)) {
            JOptionPane.showMessageDialog(null, "Password must be 8+ chars with 1 uppercase & digit.");
            return;
        }

        String phone = JOptionPane.showInputDialog("Enter phone number (Must start with +27 and be 12 digits long):");
        if (phone == null || !isValidPhone(phone)) {
            JOptionPane.showMessageDialog(null, "Invalid phone number. It must start with +27 and have 12 digits.");
            return;
        }

        users.add(new User(username, password, phone));
        JOptionPane.showMessageDialog(null, "Registration successful.\nWelcome " + firstName + " " + lastName + "!");
    }

    // ========== Validation Methods ==========

    // Ensures password contains at least 8 characters, one uppercase, and one digit.
    private static boolean isValidPassword(String pwd) {
        return pwd.length() >= 8
                && Pattern.compile("[A-Z]").matcher(pwd).find()
                && Pattern.compile("\\d").matcher(pwd).find();
    }

    // Ensures username is 5 characters max and includes an underscore
    private static boolean isValidUsername(String username) {
        return username.length() <= 5 && username.contains("_");
    }

    // Checks that the phone starts with +27 and has exactly 12 characters
    private static boolean isValidPhone(String phone) {
        return phone.matches("^\\+27\\d{9}$");
    }

    public static User loginUser() {
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");

        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(null, "Login successful!");
                return u;
            }
        }

        JOptionPane.showMessageDialog(null, "Login failed.");
        return null;
    }
}
 

