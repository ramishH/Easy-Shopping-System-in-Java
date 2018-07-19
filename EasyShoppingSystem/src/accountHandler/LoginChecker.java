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
import EasyShoppingSystem.myArrayList;
import prompt.Alert;


/**
 *
 * @author zainm
 */
public class LoginChecker 
{
    static String userFilePath = "files//User//usernames.txt";
    static String userPassPath = "files//User//passwords.txt";
    static String adminFilePath = "files//Admin//adminUsername.txt";
    static String adminPassPath = "files//Admin//adminPassword.txt";
    
    private String userName;
    private String userPassword;
    
   // private Scanner sc;
    
    FileReader fr;
   static File userFile = new File("files//User//usernames.txt");
   static File userPass = new File("files//User//passwords.txt");
   static File adminFile = new File("files//Admin//adminUsername.txt");
   static File adminPass = new File("files//Admin//adminPassword.txt");
    
    public LoginChecker()
    {
        
    }
    public LoginChecker(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
     public boolean checkAdmin(String userName, String userPassword) throws FileNotFoundException, IOException{
        if(!adminFile.exists())
        {
            adminFile.createNewFile();
        }
        if(!adminPass.exists())
        {
            adminPass.createNewFile();
        }
        boolean flag = false;
        
        FileReader ufr = new FileReader(adminFile);
        FileReader pfr = new FileReader(adminPass);
        
        BufferedReader ubr = new BufferedReader(ufr);
        BufferedReader pbr = new BufferedReader(pfr);
        
        myArrayList<String> uName = new myArrayList<>();
        //ArrayList<String> uPass = new ArrayList<>();
        
        for (int i = 0; i < lineNum(adminFilePath); i++) {
            
            uName.add(ubr.readLine());
            //uPass.add(pbr.readLine());
        }
        for (int i = 0; i < lineNum(adminFilePath); i++) {
            if (this.userName.equalsIgnoreCase(uName.get(i))){
                flag = true;
                break;
            }
        }
        return flag;
    }
     
      public boolean checkUser(String userName) //throws FileNotFoundException, IOException
      {
      boolean flag = false;
      try 
      {   
          if(!userFile.exists())
        {
            userFile.createNewFile();
        }
        if(!userPass.exists())
        {
            userPass.createNewFile();
        }
 
        FileReader ufr = new FileReader(userFile);
       // FileReader pfr = new FileReader(userPass);
        
        BufferedReader ubr = new BufferedReader(ufr);
       // BufferedReader pbr = new BufferedReader(pfr);
        
        myArrayList<String> uName = new myArrayList<>();
       // ArrayList<String> uPass = new ArrayList<>();
        
        for (int i = 0; i < lineNum(userFilePath); i++) {
            
            uName.add(ubr.readLine());
           // uPass.add(pbr.readLine());
        }
        for (int i = 0; i < lineNum(userFilePath); i++) {
            if (this.userName.equalsIgnoreCase(uName.get(i))){
                flag = true;
                break;
            }
        }
        
      }catch (Exception e)
      {
          e.printStackTrace();
      }
      return flag;
    }
     
    public int lineNum(String path) throws FileNotFoundException, IOException{
        LineNumberReader lnr = new LineNumberReader(new FileReader(path));
        lnr.skip(Long.MAX_VALUE);
        int ln = lnr.getLineNumber()+1;
        lnr.close();
       // System.out.println(ln);
        return ln;
    }
   
    public int userLogIn(String userName, String password) throws FileNotFoundException, IOException{
        if(!userFile.exists())
        {
            userFile.createNewFile();
        }
        if(!userPass.exists())
        {
            userPass.createNewFile();
        }
        
        boolean userMissing = true;
        int value = 0;
        
        FileReader ufr = new FileReader(userFile);
        FileReader pfr = new FileReader(userPass);
        
        BufferedReader ubr = new BufferedReader(ufr);
        BufferedReader pbr = new BufferedReader(pfr);
        
        ArrayList<String> uName = new ArrayList<>();
        ArrayList<String> uPass = new ArrayList<>();
        for (int i = 0; i < lineNum(userFilePath); i++) {
            uName.add(ubr.readLine());
            uPass.add(pbr.readLine());
        }
        for (int i = 0; i < lineNum(userFilePath); i++) {
        if (this.userName.equalsIgnoreCase(uName.get(i)))
        {
            userMissing  = false;
            if (this.userPassword.equals(uPass.get(i))){
                break;
            }
            else
            {
                value = 1;
                break;
            }
        }
        }
        if (userMissing)
        {
            value = -1;
        }
        ufr.close();
        return value;
    }
  
    public int adminLogIn(String userName, String password) throws FileNotFoundException, IOException{
        if(!adminFile.exists())
        {
            adminFile.createNewFile();
        }
        if(!adminPass.exists())
        {
            adminPass.createNewFile();
        }
        
        boolean userMissing = true;
        int value = 0;
        
        FileReader ufrr = new FileReader(adminFile);
        FileReader pfr = new FileReader(adminPass);
        
        BufferedReader ubr = new BufferedReader(ufrr);
        BufferedReader pbr = new BufferedReader(pfr);
        
        myArrayList<String> uName = new myArrayList<>();
        myArrayList<String> uPass = new myArrayList<>();
        for (int i = 0; i < lineNum(adminFilePath); i++) {
            uName.add(ubr.readLine());
            uPass.add(pbr.readLine());
        }
        for (int i = 0; i < lineNum(adminFilePath); i++) {
        if (this.userName.equalsIgnoreCase(uName.get(i)))
        {
            userMissing  = false;
            if (this.userPassword.equals(uPass.get(i))){
                break;
            }
            else
            {
                value = 1;
                break;
            }
        }
        }
        if (userMissing)
        {
            value = -1;
        }
        ufrr.close();
        return value;
    }
    
    public void userSettings(String userName, String password, File nameFile,File passFile, String fileadd) {
        
//        File tempNameFile = new File(nameFile);
//        File tempPassFile = new File(passFile);
        try
        {
        
        if(!nameFile.exists())
        {
            nameFile.createNewFile();
        }
        if(!passFile.exists())
        {
            passFile.createNewFile();
        }
       
        FileReader ufr = new FileReader(nameFile);
        FileReader pfr = new FileReader(passFile);
        
        BufferedReader ubr = new BufferedReader(ufr);
        BufferedReader pbr = new BufferedReader(pfr);
        
        ArrayList<String> uName = new ArrayList<>();
        ArrayList<String> uPass = new ArrayList<>();
        for (int i = 0; i < lineNum(fileadd); i++) {
            uName.add(ubr.readLine());
            uPass.add(pbr.readLine());
        }
        for (int i = 0; i < lineNum(fileadd); i++) 
        {
            if (userName.equals(uName.get(i)))
               {
                   uPass.set(i, password);
                }  
        }
        
        ufr.close();
        pfr.close();
        FileWriter fw = new FileWriter(passFile);
        PrintWriter pw = new PrintWriter(fw);
        pw.print("");
        
        FileWriter fw2 = new FileWriter(passFile, true);
        PrintWriter pw2 = new PrintWriter(fw2, true);
        
            for (int j = 0; j < uPass.size() -1; j++)
            {
                
                pw.print(uPass.get(j));
                pw.println();
                
            }
            pw.close();
        }catch(Exception e)
          {
                e.printStackTrace();
          }
       
    }
    
    
}
