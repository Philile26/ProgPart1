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
        System.out.println(login.returnLoginStatus(loggedIn));
    }
}
           


       
    

    

