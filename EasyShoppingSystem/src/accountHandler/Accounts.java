/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accountHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import prompt.Alert;

/**
 *
 * @author zainm
 */
public abstract class Accounts 
{
    
    static String userFilePath = "files//User//usernames.txt";
    static String userPassPath = "files//User//passwords.txt";
    static String adminFilePath = "files//Admin//adminUsername.txt";
    static String adminPassPath = "files//Admin//adminPassword.txt";
    
    
   // private Scanner sc;
    
    FileReader fr;
   static File userFile = new File("files//User//usernames.txt");
   static File userPass = new File("files//User//passwords.txt");
   static File adminFile = new File("files//Admin//adminUsername.txt");
   static File adminPass = new File("files//Admin//adminPassword.txt");
   
   
    
     Stage window;
     Button login, back;
     Label username, pass, headingg;
     TextField name;
     PasswordField pas;
     Scene adminn;
     boolean flag = true;
    File f;
    FileWriter pw;
    BufferedWriter bw;
    
    GridPane adminpan = new GridPane();
    
    
    public void start(Stage primaryStage) throws InterruptedException 
    {
       window = primaryStage;
       setScene();
    
    }
    
    public void createAcc(String fname, String tf)
    {
       // String newname = fname+path+".txt";
        f = new File(fname);
        try
        {
           pw = new FileWriter(f,true);
           bw = new BufferedWriter(pw);
           bw.write(tf);
           bw.newLine();
       
           bw.close();
           pw.close();
        
        } catch (Exception e)
        
        {
            Alert.display("Error", "Problem creating account");
            //System.out.println("Error creating account");
            flag = false;
        }
        //Alert.display("Congratulations","Account created successfully");
        
    }
    
    public boolean sendresult()
    {
        if(flag)
        {
            return true;
        }
        else
            return false;
    }
    
    
    
    public abstract void setScene();
    public abstract void setButtonsAction(Scene main);
    public abstract void displayPanel(Scene main );
    public abstract void displayPanel(Scene back, String username);
}
