/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.progpart1;

/**
 *
 * @author Student
 */
public class Login {
    // attributes
    public String name;
    public String cellphonenumber;
    public String username;
    public String password;
    

    // Constructor
    public Login(String name, String cellphonenumber, String username, String password) {
        this.name = name;
        this.cellphonenumber = cellphonenumber;
        this.username = username;
        this.password = password;
    }

    public boolean checkUserName() {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean checkcellphonenumber() {
        return cellphonenumber != null && cellphonenumber.startsWith("+27") && cellphonenumber.length() == 12;
    }

    public boolean checkPasswordComplexity() {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) hasCapital = true;
            if (Character.isDigit(c)) hasNumber = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }
        
         if (!checkcellphonenumber()) {
            return "CellNumber is not  correctly formatted please ensure that your cell number start wiwth the internation counttry code (+27).";
        }
        return "Username and password have been successfully captured";
    }

    public boolean loginUser(String enterUser, String enterPass) {
        return username != null && password != null && username.equals(enterUser) && password.equals(enterPass);
    }

    public String returnLoginStatus(boolean loggedIn) {
        if (loggedIn) {
            return "Welcome " + name + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again";
        }
    }
}
