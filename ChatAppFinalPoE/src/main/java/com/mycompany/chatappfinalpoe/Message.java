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
public class Message {

    private static int idCounter = 1;
    private int messageID;
    private String recipient;
    private String messageText;

    public Message(String recipient, String messageText) {
        this.messageID = idCounter++;
        this.recipient = recipient;
        this.messageText = messageText;
    }

    public int getMessageID() {
        return messageID;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    // Returns a formatted hash: first 2 digits of ID, messageID, first and last word
    public String getMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String first = words.length > 0 ? words[0] : "";
        String last = words.length > 1 ? words[words.length - 1] : first;

        // Ensures 2-digit ID prefix (e.g., 01, 02, ..., 99)
        String idPrefix = String.format("%02d", messageID % 100);

        return (idPrefix + ":" + messageID + ":" + first + last).toUpperCase();
    }

    public String getSummary() {
        return "ID: " + messageID
                + ", Hash: " + getMessageHash()
                + ", Characters: " + messageText.length()
                + "\nTo: " + recipient
                + "\nMsg: " + messageText + "\n";
    }

}
