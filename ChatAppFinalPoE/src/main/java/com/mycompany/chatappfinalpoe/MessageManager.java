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
 * This project was independently created by Princess Nqobile Mhlongo in
 * fulfilment of the Final Programming PoE (2025), developed using Java,
 * NetBeans, and Maven, and incorporating JOptionPane for the user interface.
 * While the majority of this system is original, the following resources were
 * consulted to support design and implementation:
 *
 * Title: Java User Input Tutorial Author: W3Schools Date Accessed: 19 April
 * 2025 Available: https://www.w3schools.com/java/java_user_input.asp
 *
 * Title: How to Validate Phone Numbers in Java Author: Baeldung Date Accessed:
 * 21 April 2025 Available:
 * https://www.baeldung.com/java-regex-validate-phone-numbers
 *
 * Title: JSON.simple Java JSON Encoding Example Author: CodeJava.net Date
 * Accessed: 4 May 2025 Available:
 * https://www.codejava.net/java-se/file-io/write-json-to-file
 *
 * Title: How to Run Unit Tests with JUnit 5 Author: JUnit Official Docs Date
 * Accessed: 10 May 2025 Available:
 * https://junit.org/junit5/docs/current/user-guide/
 *
 * ChatGPT by OpenAI (2025) was used under active supervision of the student
 * between April–June 2025, for debugging assistance, code review, enhancement
 * guidance, and test generation. Notable collaborative improvements included: -
 * Refactoring registration logic for username, phone, and password validation
 * (April 2025) - Generating unique Message ID and Hash formats and adjusting
 * search/delete logic (May 2025) - Designing meaningful unit tests to ensure
 * code quality (May 2025) - Maven dependency configuration and JSON
 * implementation with manual output writing (June 2025) - Strengthening error
 * handling, user flow, and UI structure using JOptionPane dialogs (June 2025)
 *
 * All code provided through assistance was adapted to suit the student’s own
 * logic, structure, and original feature requirements. The student has ensured
 * full understanding of every implemented function.
 */
import javax.swing.*;
import java.util.ArrayList;

/**
 * This class manages sending, storing, searching, and deleting messages.
 * Created by a student for the final Programming PoE in 2025.
 */
public class MessageManager {

    // List to hold all messages sent during the session
    private static ArrayList<Message> messages = new ArrayList<>();

    /**
     * This method allows the user to send multiple messages. It asks how many
     * they want to send, and handles message validation and user choices.
     */
    public static void sendMessage() {
        String input = JOptionPane.showInputDialog("How many messages would you like to send?");
        if (input == null) {
            return;
        }

        try {
            int count = Integer.parseInt(input);
            for (int i = 0; i < count; i++) {

                // Validate recipient phone number to ensure it starts with +27 and has 12 digits total
                String recipient = JOptionPane.showInputDialog("Enter recipient phone number (must start with +27):");
                if (recipient == null || !recipient.matches("\\+27\\d{9}")) {
                    JOptionPane.showMessageDialog(null, "Invalid recipient number. It must start with +27 and be 12 digits long.");
                    i--; // Retry this message
                    continue;
                }

                // Ask the user for the message content
                String messageText = JOptionPane.showInputDialog("Enter message (max 250 characters):");
                if (messageText == null) {
                    return;
                }

                // Check if message exceeds the length limit
                if (messageText.length() > 250) {
                    JOptionPane.showMessageDialog(null, "Message too long by " + (messageText.length() - 250) + " characters.");
                    i--; // Retry this message
                    continue;
                }

                // Let the user decide what to do with the message
                String[] options = {"Send", "Discard", "Store Only"};
                int choice = JOptionPane.showOptionDialog(null,
                        "Choose an action:",
                        "Message Options",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (choice == 0 || choice == 2) {
                    // Message is stored if "Send" or "Store Only" is selected
                    Message msg = new Message(recipient, messageText);
                    messages.add(msg);
                    JOptionPane.showMessageDialog(null, "Message stored successfully.\n" + msg.getSummary());
                } else {
                    JOptionPane.showMessageDialog(null, "Message discarded.");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number entered.");
        }
    }

    /**
     * This method finds and shows the longest message based on its length.
     * Helpful for analysis and display.
     */
    public static void viewLongestMessage() {
        if (messages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages to view.");
            return;
        }

        Message longest = messages.get(0);
        for (Message msg : messages) {
            if (msg.getMessageText().length() > longest.getMessageText().length()) {
                longest = msg;
            }
        }

        JOptionPane.showMessageDialog(null, "Longest Message:\n" + longest.getSummary());
    }

    /**
     * This method allows the user to search for a message by ID or Hash.
     */
    public static void searchMessage() {
        String[] options = {"By ID", "By Hash"};
        int choice = JOptionPane.showOptionDialog(null, "Search by:", "Search Message",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        // Search by ID
        if (choice == 0) {
            String idInput = JOptionPane.showInputDialog("Enter Message ID to search:");
            if (idInput == null) {
                return;
            }

            try {
                int id = Integer.parseInt(idInput);
                for (Message msg : messages) {
                    if (msg.getMessageID() == id) {
                        JOptionPane.showMessageDialog(null, "Message Found:\n" + msg.getSummary());
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Message with ID " + id + " not found.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid ID format.");
            }
        } // Search by Hash
        else if (choice == 1) {
            String hash = JOptionPane.showInputDialog("Enter Message Hash:");
            if (hash == null) {
                return;
            }

            for (Message msg : messages) {
                if (msg.getMessageHash().equalsIgnoreCase(hash)) {
                    JOptionPane.showMessageDialog(null, "Message Found:\n" + msg.getSummary());
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Message with Hash " + hash + " not found.");
        }
    }

    /**
     * Deletes a message based on its hash code. This was a required feature in
     * the project brief.
     */
    public static void deleteMessage() {
        if (messages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages to delete.");
            return;
        }

        String hash = JOptionPane.showInputDialog("Enter the Message Hash to delete:");
        if (hash == null) {
            return;
        }

        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getMessageHash().equalsIgnoreCase(hash)) {
                messages.remove(i);
                JOptionPane.showMessageDialog(null, "Message deleted successfully.");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "No message found with the given hash.");
    }

    /**
     * Displays a full report of all messages in the system. Useful for
     * generating a final summary for evaluation.
     */
    public static void showFullReport() {
        if (messages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages to report.");
            return;
        }

        StringBuilder report = new StringBuilder("Full Message Report:\n");
        for (Message msg : messages) {
            report.append(msg.getSummary()).append("\n");
        }

        JOptionPane.showMessageDialog(null, report.toString());
    }
}
