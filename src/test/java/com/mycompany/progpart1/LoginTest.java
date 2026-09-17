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
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    
    public LoginTest() {
    }
 
       @Test
    public void testCheckUserNameValid() {
        System.out.println("checkUserName");
        Login instance = new Login("Kyle", "+27838968976", "kyl_1", "Ch&&sec@ke99!");
        boolean expResult = true;
        boolean result = instance.checkUserName();
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckUserNameInvalid() {
        System.out.println("checkUserName");
        Login instance = new Login("Kyle", "+27838968976", "kyle!!!!!!", "Ch&&sec@ke99!");
        boolean expResult = false;
        boolean result = instance.checkUserName();
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckcellphonenumberValid() {
        System.out.println("checkcellphonenumber");
        Login instance = new Login("Kyle", "+27838968976", "kyl_1", "Ch&&sec@ke99!");
        boolean expResult = true;
        boolean result = instance.checkcellphonenumber();
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckcellphonenumberInValid() {
        System.out.println("checkcellphonenumber");
        Login instance = new Login("Kyle", "0838968976", "kyl_1", "Ch&&sec@ke99!");
        boolean expResult = false;
        boolean result = instance.checkcellphonenumber();
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckPasswordComplexityValid() {
        System.out.println("checkPasswordComplexity");
        Login instance = new Login("Kyle", "+27838968976", "kyl_1", "Ch&&sec@ke99!");
        boolean expResult = true;
        boolean result = instance.checkPasswordComplexity();
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckPasswordComplexityInvalid() {
        System.out.println("checkPasswordComplexity");
        Login instance = new Login("Kyle", "+27838968976", "kyl_1", "password");
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity();
        assertEquals(expResult, result);
    }

    @Test
    public void testRegisterUserSuccess() {
        System.out.println("registerUser");
        Login instance = new Login("Kyle", "+27838968976", "kyl_1", "Ch&&sec@ke99!");
        String expResult = "Username and password have been successfully captured";
        String result = instance.registerUser();
        assertEquals(expResult, result);
    }

    @Test
    public void testRegisterUserInvalidUsername() {
        System.out.println("registerUser");
        Login instance = new Login("Kyle", "+27838968976", "ky!!!!!!!!!!!!!!!!!!!!!!", "Ch&&sec@ke99!");
        String expResult =  "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        String result = instance.registerUser();
        assertEquals(expResult, result);
    }

    @Test
    public void testRegisterUserInvalidPassword() {
        System.out.println("registerUser");
        Login instance = new Login("Kyle", "+27838968976", "kyl_1", "Ch&99!");
        String expResult =  "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        String result = instance.registerUser();
        assertEquals(expResult, result);
    }

    @Test
    public void testRegisterUserInvalidCellPhone() {
        System.out.println("registerUser");
        Login instance = new Login("Kyle", "08966553", "kyl_1", "Ch&&sec@ke99!");
        String expResult = "CellNumber is not  correctly formatted please ensure that your cell number start wiwth the internation counttry code (+27).";
        String result = instance.registerUser();
        assertEquals(expResult, result);
    }

    @Test
    public void testLoginUserSuccess() {
        System.out.println("loginUser");
        String enterUser = "kyl_1";
        String enterPass = "Ch&&sec@ke99!";
        Login instance = new Login("Kyle", "+27838968976", "kyl_1", "Ch&&sec@ke99!");
        boolean expResult = true;
        boolean result = instance.loginUser(enterUser, enterPass);
        assertEquals(expResult, result);
    }

    @Test
    public void testLoginUserFailure() {
        System.out.println("loginUser");
        String enterUser = "wrong_user";
        String enterPass = "wrong_pass";
        Login instance = new Login("Kyle", "+27838968976", "kyl_1", "Ch&&sec@ke99!");
        boolean expResult = false;
        boolean result = instance.loginUser(enterUser, enterPass);
        assertEquals(expResult, result);
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        System.out.println("returnLoginStatus");
        boolean loggedIn = true;
        Login instance = new Login("Kyle", "+27838968976", "kyl_1", "Ch&&sec@ke99!");
        String expResult = "Welcome Kyle it is great to see you again.";
        String result = instance.returnLoginStatus(loggedIn);
        assertEquals(expResult, result);
    }

    @Test
    public void testReturnLoginStatusFailure() {
        System.out.println("returnLoginStatus");
        boolean loggedIn = false;
        Login instance = new Login("Kyle", "+27838968976", "kyl_1", "Ch&&sec@ke99!");
        String expResult = "Username or password incorrect, please try again";
        String result = instance.returnLoginStatus(loggedIn);
        assertEquals(expResult, result);
    }
}
