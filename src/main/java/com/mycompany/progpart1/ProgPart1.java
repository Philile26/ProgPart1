/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.progpart1;
import java.util.Scanner;
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

        // --- Users define how many messages they wish to enter ---
        System.out.print("How many messages do you wish to send? ");
        int numMessagesToSend = input.nextInt();
        input.nextLine(); // Consume newline

        boolean running = true;
        int messagesSentCount = 0; // Keep track of actual sent messages

        // --- While Loop for Menu ---
        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("Option 1) Send Messages");
            System.out.println("Option 2) Show recently sent messages");
            System.out.println("Option 3) Quit");
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

                        // Create Message Object
                        Message msg = new Message(recipient, messageText, i);
                        
                        // Validate Recipient Format
                        System.out.println(msg.checkRecipientCell());

                        // Display Message Details
                        System.out.println("\n--- Message Details ---");
                        System.out.println(msg.printMessages());

                        // Prompt action (Send, Disregard, Store)
                        String actionResult = msg.SentMessage();
                        System.out.println(actionResult);

                        // Only count if actually sent (Selecting option 1)
                        if (actionResult.equals("Message successfully sent.")) {
                            messagesSentCount++;
                        }
                    }
                    
                    // Total Messages Displayed
                    System.out.println("\nTotal messages successfully sent: " + messagesSentCount);
                    break;

                case 2:
                    // Feature still in development
                    System.out.println("Coming Soon.");
                    break;

                case 3:
                    // Exit the application
                    System.out.println("Exiting QuickChat. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
                    
            }
        }
       
        }
        
    }
}
           


       
    

    

