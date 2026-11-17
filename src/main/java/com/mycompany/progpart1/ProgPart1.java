/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.progpart1;
import java.util.Scanner;
import java.util.List;
/**
 *
 * @author Student
 */
public class ProgPart1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter your name:");
        String name = input.nextLine();

        System.out.print("Please enter your cellphone number:");
        String cellphonenumber = input.nextLine();

        System.out.print("Please enter your username:");
        String username = input.nextLine();

        System.out.print("Please enter your password:");
        String password = input.nextLine();

        //Create an instance(object) of Login class
        Login login = new Login(name, cellphonenumber, username, password);

        //check whether the username meets the requirements
        if(login.checkUserName()){
            System.out.println("Username successfully captured");
        }else{
            System.out.println("Username is not successfully captured");
        }

        //checking if the student number meets the requirements
        if(login.checkcellphonenumber()){
            System.out.println("cellphone number successfully captured");
        }else{
            System.out.println("cellphone number is not successfully captured");
        }

        //check password complexity
        if(login.checkPasswordComplexity()){
            System.out.println("Password successfully captured");
        }else{
            System.out.println("Password is not successfully captured");
        }

        //display registration status message
        System.out.println(login.registerUser());

        //login check
        System.out.print("Please enter username to login:");
        String enterUser = input.nextLine();

        System.out.print("Please enter password to login:");
        String enterPass = input.nextLine();

        boolean loggedIn = login.loginUser(enterUser, enterPass);
        if(loggedIn){
            
            System.out.println(login.returnLoginStatus(loggedIn));
            // --- Welcome Message ---
            System.out.println("Welcome to QuickChat.");

            // Initialize MessageManager for Part 3 Array & JSON tracking
            MessageManager manager = new MessageManager();

            // --- Users define how many messages they wish to enter ---
            System.out.print("How many messages do you wish to send? ");
            int numMessagesToSend = input.nextInt();
            input.nextLine(); // Consume newline

            boolean running = true;

            // --- While Loop for Menu ---
            while (running) {
                System.out.println("\n--- MENU ---");
                System.out.println("Option 1) Send Messages");
                System.out.println("Option 2) Show Recently Sent Messages Count");
                System.out.println("Option 3) Load Stored Messages from JSON");
                System.out.println("Option 4) Stored Messages & Reports Menu (Part 3)");
                System.out.println("Option 5) Quit");
                System.out.print("Choose an option: ");
                int menuChoice = input.nextInt();
                input.nextLine(); // Consume newline

                switch (menuChoice) {
                    case 1:
                        // --- For Loop for assigned number of messages  ---
                        for (int i = 0; i < numMessagesToSend; i++) {
                            System.out.println("\n--- Message " + (i + 1) + " of " + numMessagesToSend + " ---");
                            
                            // Recipient Input
                            System.out.print("Enter recipient cell number (include international code like +27...): ");
                            String recipient = input.nextLine();

                            // Message Input
                            String messageText = "";
                            boolean validLength = false;
                            while (!validLength) {
                                System.out.print("Enter message (max 250 characters): ");
                                messageText = input.nextLine();

                                if (messageText.length() > 250) {
                                    int excess = messageText.length() - 250;
                                    System.out.println("Message exceeds 250 characters by " + excess + "; please reduce the size.");
                                } else {
                                    validLength = true; // Exit loop if valid
                                }
                            }

                            // Prompt action to get the flag ("Sent", "Disregard", or "Stored")
                            System.out.println("Choose an option: 1) Send Message  2) Disregard Message  3) Store Message");
                            int action = input.nextInt();
                            input.nextLine(); // Consume newline

                            String flag = (action == 1) ? "Sent" : (action == 2) ? "Disregard" : "Stored";

                            // Create Message Object with the new 4-argument constructor
                            Message msg = new Message(recipient, messageText, i + 1, flag);
                            
                            // Validate Recipient Format
                            System.out.println(msg.checkRecipientCell());

                            // Display Message Details
                            System.out.println("\n--- Message Details ---");
                            System.out.println(msg.printMessages());

                            if (action == 1) {
                                System.out.println("Message successfully sent.");
                            } else if (action == 2) {
                                System.out.println("Press 0 to delete the message.");
                            } else if (action == 3) {
                                msg.storeMessage();
                                System.out.println("Message successfully stored.");
                            }

                            // Add to MessageManager parallel arrays for Part 3 tracking
                            manager.addMessageRecord(username, msg);
                        }
                        break;

                    case 2:
                        System.out.println("Total Sent Messages: " + manager.sentMessages.size());
                        break;

                    case 3:
                        manager.loadStoredMessagesFromJSON("messages.json");
                        System.out.println("Stored messages successfully loaded from JSON.");
                        break;

                    case 4:
                        showStoredMessagesMenu(manager, input);
                        break;

                    case 5:
                        // Exit the application
                        System.out.println("Exiting QuickChat. Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option. Please choose between 1 and 5.");
                }
            }
        } else {
            System.out.println(login.returnLoginStatus(loggedIn));
        }
        input.close();
    }

    // Sub-menu helper for Part 3 reports and searches (a - f)
    private static void showStoredMessagesMenu(MessageManager manager, Scanner input) {
        System.out.println("\n--- STORED MESSAGES & REPORTS MENU ---");
        System.out.println("a. Display sender and recipient of all messages");
        System.out.println("b. Display the longest message");
        System.out.println("c. Search for a Message ID");
        System.out.println("d. Search messages for a particular recipient");
        System.out.println("e. Delete a message using Message Hash");
        System.out.println("f. Display full Sent Messages Report");
        System.out.print("Select sub-option (a-f): ");
        
        String subChoice = input.nextLine().toLowerCase();

        switch (subChoice) {
            case "a":
                System.out.println(manager.displaySendersAndRecipients());
                break;
            case "b":
                System.out.println("Longest Message: " + manager.getLongestMessage());
                break;
            case "c":
                System.out.print("Enter Message ID: ");
                String id = input.nextLine();
                System.out.println(manager.searchByMessageID(id));
                break;
            case "d":
                System.out.print("Enter recipient number: ");
                String rec = input.nextLine();
                List<String> found = manager.searchByRecipient(rec);
                if (found.isEmpty()) {
                    System.out.println("No messages found for this recipient.");
                } else {
                    System.out.println("Found Messages: " + String.join(" | ", found));
                }
                break;
            case "e":
                System.out.print("Enter Message Hash to delete: ");
                String hash = input.nextLine();
                System.out.println(manager.deleteByMessageHash(hash));
                break;
            case "f":
                System.out.println(manager.displaySentReport());
                break;
            default:
                System.out.println("Invalid selection. Please choose a letter from 'a' to 'f'.");
        }
    }
}