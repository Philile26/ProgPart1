/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpart1;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author Student
 */
public class Message {
      // Variables (Data types & Scope)
    private String messageID;
    private int numMessagesSent;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Constructor
    public Message(String recipient, String messageText, int numMessagesSent) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.numMessagesSent = numMessagesSent;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Method to randomly generate a 10-digit ID
    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }

    // 1. Boolean: checkMessageID()
    public boolean checkMessageID() {
        // Ensures the ID is not more than 10 characters
        return this.messageID.length() <= 10;
    }

    // 2. String: checkRecipientCell()
    public String checkRecipientCell() {
        // Note: The instruction text says "<= 10 chars", but the test data (+27718693002) 
        // is 12 chars. We check if it starts with '+' (international code) and has a valid length.
        if (recipient.startsWith("+") && recipient.length() >= 10 && recipient.length() <= 15) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // 3. String: createMessageHash()
    public String createMessageHash() {
        // Hash contains: first 2 digits of ID + ":" + message number + ":" + first & last word
        String firstTwoID = messageID.substring(0, 2);
        
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        // Remove punctuation from the last word if there is any (like '?')
        lastWord = lastWord.replaceAll("[^a-zA-Z]", "");

        String hash = firstTwoID + ":" + numMessagesSent + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    // 4. String: SentMessage()
    public String SentMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an option: 1) Send Message  2) Disregard Message  3) Store Message");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            storeMessage(); // Research requirement: Store as JSON
            return "Message successfully stored.";
        } else {
            return "Invalid choice.";
        }
    }

    // 5. String: printMessages()
    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageText;
    }

    // 6. Int: returnTotalMessages()
    public int returnTotalMessages() {
        return numMessagesSent;
    }

    // 7. Research: storeMessage() - simple JSON format file writer
    public void storeMessage() {
        try {
            FileWriter file = new FileWriter("messages.json", true); // true = append mode
            // Writing a simple JSON structure
            String json = "{\n" +
                    "  \"id\": \"" + messageID + "\",\n" +
                    "  \"recipient\": \"" + recipient + "\",\n" +
                    "  \"message\": \"" + messageText + "\",\n" +
                    "  \"hash\": \"" + messageHash + "\"\n" +
                    "}\n";
            file.write(json);
            file.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
        }
    }

    // Getters for testing and main class
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
}



