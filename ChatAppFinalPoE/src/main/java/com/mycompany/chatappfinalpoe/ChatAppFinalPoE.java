/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
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


// Main class to start the application using a menu-driven JOptionPane interface,handles user registration, login, and access to the message menu.


public class ChatAppFinalPoE {
      public static void main(String[] args) {

// Infinite loop to show main menu until user exits
        while (true) {
            String[] options = {"Register", "Login", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Welcome", "Main Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                    options, options[0]);

            if (choice == 0) {
                RegistrationLogin.registerUser();
            } else if (choice == 1) {
                User user = RegistrationLogin.loginUser();
                if (user != null) {
                    messageMenu(user);
                }
            } else {
                break; // Exit the application
            }
        }
    }
// Menu after login for sending, managing and exporting messages

    private static void messageMenu(User user) {
        while (true) {
            String[] menu = {"Send Message", "View Longest", "Search", "Delete", "Report", "Logout"};
            int opt = JOptionPane.showOptionDialog(null, "Welcome " + user.getUsername(),
                    "Messaging Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, menu, menu[0]);

            switch (opt) {
                case 0 ->
                    MessageManager.sendMessage();  // Send new message
                case 1 ->
                    MessageManager.viewLongestMessage(); //Show longest message
                case 2 ->
                    MessageManager.searchMessage(); //search by ID or hash
                case 3 ->
                    MessageManager.deleteMessage(); //deletes a message
                case 4 ->
                    MessageManager.showFullReport();  //show full message summary 
                case 5 -> {
                    return; //Logout
                }
            }
        }
    }
}


    