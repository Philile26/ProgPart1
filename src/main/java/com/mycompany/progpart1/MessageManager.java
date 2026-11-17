/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpart1;

/*
 * =====================================================================
 * Attribution & References:
 * JSON parsing and file reading functionality adapted from 
 * JSON.simple Library Documentation and Oracle Java tutorials.
 * =====================================================================
 */

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MessageManager {
    // Parallel arrays required for Part 3
    public List<String> sentMessages = new ArrayList<>();
    public List<String> disregardedMessages = new ArrayList<>();
    public List<String> storedMessages = new ArrayList<>();
    public List<String> messageHashes = new ArrayList<>();
    public List<String> messageIDs = new ArrayList<>();
    
    public List<String> allSenders = new ArrayList<>();
    public List<String> allRecipients = new ArrayList<>();
    public List<String> allMessages = new ArrayList<>();
    public List<String> allFlags = new ArrayList<>();

    public void addMessageRecord(String sender, Message msg) {
        allSenders.add(sender);
        allRecipients.add(msg.getRecipient());
        allMessages.add(msg.getMessageText());
        allFlags.add(msg.getFlag());
        messageIDs.add(msg.getMessageID());
        messageHashes.add(msg.getMessageHash());

        if (msg.getFlag() != null) {
            if (msg.getFlag().equalsIgnoreCase("Sent")) {
                sentMessages.add(msg.getMessageText());
            } else if (msg.getFlag().equalsIgnoreCase("Disregarded") || msg.getFlag().equalsIgnoreCase("Disregard")) {
                disregardedMessages.add(msg.getMessageText());
            } else if (msg.getFlag().equalsIgnoreCase("Stored")) {
                storedMessages.add(msg.getMessageText());
            }
        }
    }

    // Load stored messages from JSON
    public void loadStoredMessagesFromJSON(String filePath) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            for (Object obj : jsonArray) {
                JSONObject jsonObj = (JSONObject) obj;
                String recipient = (String) jsonObj.get("recipient");
                String message = (String) jsonObj.get("message");
                
                Message msg = new Message(recipient, message, storedMessages.size() + 1, "Stored");
                addMessageRecord("Developer", msg);
            }
        } catch (Exception e) {
            System.out.println("JSON Error: " + e.getMessage());
        }
    }

    // a. Display sender and recipient of all messages
    public String displaySendersAndRecipients() {
        StringBuilder sb = new StringBuilder("--- Senders and Recipients ---\n");
        for (int i = 0; i < allMessages.size(); i++) {
            sb.append("Sender: ").append(allSenders.get(i))
              .append(" | Recipient: ").append(allRecipients.get(i)).append("\n");
        }
        return sb.toString();
    }

    // b. Display the longest message
    public String getLongestMessage() {
        String longest = "";
        for (String msg : allMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        return longest;
    }

    // c. Search for a Message ID
    public String searchByMessageID(String searchID) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(searchID)) {
                return "Recipient: " + allRecipients.get(i) + "\nMessage: " + allMessages.get(i);
            }
        }
        return "Message ID not found.";
    }

    // d. Search messages for a particular recipient
    public List<String> searchByRecipient(String recipient) {
        List<String> results = new ArrayList<>();
        for (int i = 0; i < allRecipients.size(); i++) {
            if (allRecipients.get(i).equals(recipient)) {
                results.add(allMessages.get(i));
            }
        }
        return results;
    }

    // e. Delete a message using Message Hash
    public String deleteByMessageHash(String hash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equalsIgnoreCase(hash)) {
                String deleted = allMessages.get(i);
                allMessages.remove(i);
                allRecipients.remove(i);
                allSenders.remove(i);
                allFlags.remove(i);
                messageIDs.remove(i);
                messageHashes.remove(i);
                return "Message \"" + deleted + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    // f. Display full Sent Messages Report
    public String displaySentReport() {
        StringBuilder report = new StringBuilder("=== SENT MESSAGES REPORT ===\n");
        for (int i = 0; i < allMessages.size(); i++) {
            if (allFlags.get(i).equalsIgnoreCase("Sent")) {
                report.append("Hash: ").append(messageHashes.get(i))
                      .append(" | Recipient: ").append(allRecipients.get(i))
                      .append(" | Message: ").append(allMessages.get(i)).append("\n");
            }
        }
        return report.toString();
    }
}