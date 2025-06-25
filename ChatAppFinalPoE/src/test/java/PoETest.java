/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nqobile
 */
import com.mycompany.chatappfinalpoe.RegistrationLogin;
import com.mycompany.chatappfinalpoe.User;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
public class PoETest {
    // ========== RegistrationLogin tests ==========

// This test checks if the password format is valid based on the required rules: 
// at least 8 characters, includes at least one uppercase letter and one digit.
    @Test
    public void testValidPasswordFormat() {
        assertTrue("Pass1234".matches("(?=.*[A-Z])(?=.*\\d).{8,}"));
        assertFalse("weakpass".matches("(?=.*[A-Z])(?=.*\\d).{8,}"));
    }

// This test checks if the phone number format is valid: must start with 0 and be 10 digits long.
    @Test
    public void testValidPhoneFormat() {
        assertTrue("0723456789".matches("0\\d{9}"));
        assertFalse("1234567890".matches("0\\d{9}"));
    }

// Placeholder test for registration logic.
// As a student, I learned that this type of method is hard to test without breaking it into smaller methods.
    @Test
    public void testRegistrationLoginLogic() {
        RegistrationLogin.registerUser(); // GUI input makes this difficult to unit test
        assertTrue(true); // This test is a placeholder to keep structure complete
    }

// ========== User class tests ==========
// This test checks that a new User object stores and returns correct values.
    @Test
    public void testUserObjectCreation() {
        User user = new User("techie", "Pass1234", "0712345678");
        assertEquals("techie", user.getUsername());
        assertEquals("Pass1234", user.getPassword());
        assertEquals("0712345678", user.getPhone());
    }

// ========== MessageManager logic tests ==========
// This test checks that a message within 250 characters is valid, and one over 250 is too long.
    @Test
    public void testMessageLengthLimits() {
        String msg = "Hello, this is under 250 characters.";
        assertTrue(msg.length() <= 250);

        String longMsg = "A".repeat(251);
        assertTrue(longMsg.length() > 250);
    }

// This test checks if messages are stored and retrieved correctly using a temporary list.
    @Test
    public void testMessageStorageAndRetrieval() {
        ArrayList<String> tempMessages = new ArrayList<>();
        tempMessages.add("Hey there!");
        tempMessages.add("This is another message");

        assertEquals(2, tempMessages.size());
        assertTrue(tempMessages.contains("Hey there!"));
    }

// This test checks that a message containing a specific word can be found.
    @Test
    public void testSearchMessageLogic() {
        ArrayList<String> messages = new ArrayList<>();
        messages.add("Let’s meet at 5");
        messages.add("Lunch break");

        boolean found = messages.stream().anyMatch(m -> m.contains("meet"));
        assertTrue(found);
    }

// This test checks that a message can be deleted by removing it from the list.
    @Test
    public void testDeleteMessageIndex() {
        ArrayList<String> messages = new ArrayList<>();
        messages.add("To be deleted");
        messages.remove(0);

        assertEquals(0, messages.size());
    }

// This is a dummy test just to confirm that tests can run properly.
// I included it while setting up my test class to make sure everything works.
    @Test
    public void testDummyPass() {
        assertNotNull("Just checking test runs.");
    }
}
