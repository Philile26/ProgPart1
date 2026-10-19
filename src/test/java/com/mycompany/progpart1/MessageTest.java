/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.progpart1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class MessageTest {
   // 1. Test Constructor and Getters
    @Test
    public void testConstructorAndGetters() {
        Message msg = new Message("+27718693002", "Hello", 1);
        
        assertEquals("+27718693002", msg.getRecipient());
        assertEquals("Hello", msg.getMessageText());
        assertNotNull(msg.getMessageID()); // Checks that an ID was generated
        assertNotNull(msg.getMessageHash()); // Checks that a hash was generated
    }

    // 2. Test checkMessageID() - Success
    @Test
    public void testCheckMessageID() {
        Message msg = new Message("+27718693002", "Test", 1);
        
        // The generated ID should be exactly 10 characters, so this should be true
        assertTrue(msg.checkMessageID());
    }

    // 3. Test checkRecipientCell() - Valid Number
    @Test
    public void testCheckRecipientCell_Valid() {
        Message msg = new Message("+27718693002", "Test", 1);
        
        String expected = "Cell phone number successfully captured.";
        assertEquals(expected, msg.checkRecipientCell());
    }

    // 4. Test checkRecipientCell() - Invalid Number (No '+')
    @Test
    public void testCheckRecipientCell_Invalid() {
        Message msg = new Message("0712345678", "Test", 1);
        
        String expected = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        assertEquals(expected, msg.checkRecipientCell());
    }

    // 5. Test createMessageHash()
    @Test
    public void testCreateMessageHash() {
        // Using "Hi there!" -> First word "Hi", Last word "there!" -> cleaned to "THERE"
        Message msg = new Message("+27718693002", "Hi there!", 1);
        String hash = msg.createMessageHash();
        
        // Hash format: [First 2 of ID]:[MessageNum]:[FirstWord][LastWord]
        // Since ID is random, we just check if it ends with the expected text
        assertTrue(hash.endsWith(":1:HITHERE"));
    }

    // 6. Test returnTotalMessages()
    @Test
    public void testReturnTotalMessages() {
        Message msg = new Message("+27718693002", "Test", 5);
        
        assertEquals(5, msg.returnTotalMessages());
    }

    // 7. Test printMessages()
    @Test
    public void testPrintMessages() {
        Message msg = new Message("+27718693002", "Print Me", 1);
        String output = msg.printMessages();
        
        // Check if the output string contains the right details
        assertTrue(output.contains("Message ID: "));
        assertTrue(output.contains("Recipient: +27718693002"));
        assertTrue(output.contains("Message: Print Me"));
    }

    // 8. Test SentMessage() - Option 1 (Send)
    @Test
    public void testSentMessage_Send() {
        // Simulate the user typing "1" into the console
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        
        Message msg = new Message("+27718693002", "Test", 1);
        assertEquals("Message successfully sent.", msg.SentMessage());
    }

    // 9. Test SentMessage() - Option 2 (Disregard)
    @Test
    public void testSentMessage_Disregard() {
        // Simulate the user typing "2"
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));
        
        Message msg = new Message("+27718693002", "Test", 1);
        assertEquals("Press 0 to delete the message.", msg.SentMessage());
    }

    // 10. Test SentMessage() - Option 3 (Store)
    @Test
    public void testSentMessage_Store() {
        // Simulate the user typing "3" (This will also create/append to messages.json)
        System.setIn(new ByteArrayInputStream("3\n".getBytes()));
        
        Message msg = new Message("+27718693002", "Test", 1);
        assertEquals("Message successfully stored.", msg.SentMessage());
    }

    // 11. Test SentMessage() - Invalid Option
    @Test
    public void testSentMessage_Invalid() {
        // Simulate the user typing "9" (an invalid choice)
        System.setIn(new ByteArrayInputStream("9\n".getBytes()));
        
        Message msg = new Message("+27718693002", "Test", 1);
        assertEquals("Invalid choice.", msg.SentMessage());
    }
}