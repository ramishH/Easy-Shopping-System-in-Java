/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accountHandler;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import guiPackage.History;
import guiPackage.ShoppingMenu;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.scene.layout.BorderPane;
import prompt.Alert;
import EasyShoppingSystem.Proj;
import EasyShoppingSystem.myArrayList;


/**
 *
 * @author zainm
 */
public class UserAccount extends Accounts     
{
    
    Button signup;
    String heading;
    
    @Override
    public void setScene()
    {
         window.setTitle("User account");
        GridPane userpan = new GridPane();
        userpan.setPadding(new Insets(10,10,10,10));
        userpan.setVgap(8);
        userpan.setHgap(10);
        
        headingg = new Label("User Login");
        Image image = new Image(getClass().getResourceAsStream("/res/lock.png"),35,40,true,true);
        headingg.setGraphic(new ImageView(image));
       
        GridPane.setConstraints(headingg, 1, 0);
        
        username = new Label();
        username.setText("Username");
       // username.setTextFill(Color.web("#0076a3"));
        GridPane.setConstraints(username, 0, 1);
        
        name = new TextField();
        name.setPromptText("Username");
        GridPane.setConstraints(name, 1, 1);
        
        pass = new Label();
        pass.setText("Password");
      //  pass.setTextFill(Color.web("#0076a3"));
        GridPane.setConstraints(pass, 0, 2);
        
         pas = new PasswordField();
        pas.setPromptText("Password");
        GridPane.setConstraints(pas, 1, 2);
        
        login = new Button("Login");
        GridPane.setConstraints(login, 0, 4);
        
        signup = new Button("Signup");
        GridPane.setConstraints(signup, 1, 4);
        
        back = new Button("Back");
        GridPane.setConstraints(back, 2, 4);

        userpan.getChildren().addAll(headingg,username,name,pass,pas,login,signup,back);
        
        adminn = new Scene(userpan,300,150);
        adminn.getStylesheets().add("Style.css");
        window.setScene(adminn);
    }
    
    @Override
    public void setButtonsAction(Scene main)
    {
        back.setOnAction(y -> {
            window.setScene(main) ;
            window.setTitle("***Easy Shopping System***");
                });
        
        login.setOnAction(x -> {
            
            LoginChecker lc = new LoginChecker(name.getText(),pas.getText());
              int val = 0;
               try 
               {
                   val = lc.userLogIn(name.getText(), pas.getText());
               } catch (IOException ex) 
               {
                   Logger.getLogger(Proj.class.getName()).log(Level.SEVERE, null, ex);
               }
                   if(val == 0)
                   {
              
// error = false;
               // userpanel(main);
                    displayPanel(main, name.getText());
                       
               
                   }
                   else if(val > 0 )
                   {
                       Alert.display("Error", "Password is incorrect");
                   }
                   else
                   {
                       Alert.display("Error", "Username is incorrect");
                   }
        });
        
        signup.setOnAction(e ->{
            signup(adminn);
        });
    }
    
    
   private void signup(Scene back)
   {
       window.setTitle("User Signup");
       GridPane grid = new GridPane();
       grid.setPadding(new Insets(10,10,10,10));
       grid.setVgap(20);
       grid.setHgap(8);
       
      // Label heading = new Label("User Signup");
      // GridPane.setConstraints(heading, 1, 0);
       Label lbsignup = new Label("User Signup");
       Image image = new Image(getClass().getResourceAsStream("/res/signup.png"),35,35,true,true);
       lbsignup.setGraphic(new ImageView(image));
       GridPane.setConstraints(lbsignup, 0, 0);
       
       Label fnamelabel = new Label("First Name");
       GridPane.setConstraints(fnamelabel, 0, 1);
       
       TextField firstname = new TextField();
       firstname.setPromptText("First Name");
       GridPane.setConstraints(firstname, 0, 2);
       
       Label lnamelabel = new Label("Last Name");
       GridPane.setConstraints(lnamelabel, 1, 1);
       
       TextField lastname = new TextField();
       lastname.setPromptText("Last Name");
       GridPane.setConstraints(lastname, 1, 2);
       
       Label cnumlabel = new Label("Enter Credit Card Number");
       GridPane.setConstraints(cnumlabel, 0, 3);
       
       TextField cardnum = new TextField();
       cardnum.setPromptText("Credit Card Number");
       GridPane.setConstraints(cardnum, 0, 4);
       
       Label unamelabel = new Label("Enter Username");
       GridPane.setConstraints(unamelabel, 0, 5);
               
       TextField username = new TextField();
       username.setPromptText("Username");
       GridPane.setConstraints(username, 0, 6);
       
       Label passlabel = new Label("Enter Password");
       GridPane.setConstraints(passlabel, 0, 7);
       
       PasswordField pass = new PasswordField();
       pass.setPromptText("Password");
       GridPane.setConstraints(pass, 0, 8);
       
       Label repasslabel = new Label("Re Enter Your Password");
       GridPane.setConstraints(repasslabel, 0, 9);
       
       PasswordField repass = new PasswordField();
       repass.setPromptText("Re Enter Password");
       GridPane.setConstraints(repass, 0, 10);
       
      // HBox buttons = new HBox(20);
      
       Button cancel = new Button("Cancel");
       GridPane.setConstraints(cancel, 0, 12);
      
       Button create = new Button("Creat Account");
       GridPane.setConstraints(create, 1, 12);
           
      //buttons.getChildren().addAll(cancel,create);
      
       grid.getChildren().addAll(lbsignup,fnamelabel,firstname,lnamelabel,lastname,cnumlabel,cardnum,unamelabel,username,passlabel,pass,repasslabel,repass,create,cancel);
       
      // BorderPane bp = new BorderPane();
      // bp.setCenter(grid);
      // bp.setBottom(buttons);
       
       Scene signupwindow = new Scene(grid,400,540);
       signupwindow.getStylesheets().add("Style.css");
       window.setScene(signupwindow);
       window.show();
       
       cancel.setOnAction(e -> {
           window.setScene(back);
           window.setTitle("User account");
         }
       );
       
       create.setOnAction(e -> {
           
           //check fields is empty or not
           boolean fieldflag = false, passflag = false;
           fieldflag=checkfields(firstname, lastname, cardnum, username, pass, repass);
          
           //check credit num
           boolean cnumflag =true;
           
           if(fieldflag)
           {
           String s = cardnum.getText();
           try{
               Long i = Long.parseLong(s);
           } catch (Exception z)
           {
               Alert.display("Error", "Credit number is not valid");
               cnumflag = false;
           }
           }
           //check credit card limit
           boolean limitflag = false;
           if(fieldflag && cnumflag)
           {
               int chk = 16;
               int chk2 = cardnum.getText().length();
               if(chk != chk2)
               {
                   Alert.display("Error", "Please enter 16 digit valid number");
               }
               else
                   limitflag = true;
           }
           
           //if fileds is filled check pass is match or not
           if(fieldflag && cnumflag && limitflag)
           {
              passflag=checkpass(pass,repass);
           }
           
           //to check if user name already exist
           boolean r=false;
           String fname = "files//User//usernames";
           String use = username.getText();
           String pas = pass.getText();
          /*
           UserNameChecker chk = new UserNameChecker(fname,use);
           if(chk.sendresult() && fieldflag && passflag && cnumflag)
           {
               
               Alert.display("Error", "Username is already in use");
           }
           else
           {
               if(!chk.sendresult() && fieldflag && passflag && cnumflag && limitflag)
               {
                  r = true;
               }
           }
           */
           LoginChecker chk = new LoginChecker(use,pas);
           r=chk.checkUser(use);
           if(r)
           {
               Alert.display("Error", "Username is already in use");
           }
           
           //if user name not exist than creat acc
           if(!r && fieldflag && passflag && cnumflag && limitflag)
           {
              boolean check = false;
              createAcc("files//User//Usernames.txt",use);
              createAcc("files//User//Passwords.txt",pas);
              check = sendresult();
              if(check)
              {
                 Alert.display("Congratulations","Account created successfully");
                 window.setScene(back);
              }
              
           }
      
         }
       ); // end button function
   }
   
   private boolean checkfields(TextField firstname,TextField lastname,TextField cardnum,TextField username,TextField pass,TextField repass)
   {
       if(firstname.getText().equals("") || lastname.getText().equals("") || cardnum.getText().equals("") || username.getText().equals("") || pass.getText().equals("") || repass.getText().equals(""))
       {
         Alert.display("Error", "Please fill all the fields");
         return false;
       }
       else
       {
           return true;
       }
   }
   
   private boolean checkpass(TextField pass, TextField repass)
   {
       
       if(pass.getText().equals(repass.getText()))
       {
           return true;
       }
       else
       {
           Alert.display("Error", "Password not matched");
           return false;
       }
   }
   
   public void displayPanel(Scene back, String username)
    {
       window.setTitle("User Panel");
       GridPane grid = new GridPane();
       grid.setPadding(new Insets(10,10,10,10));
       grid.setVgap(10);
       grid.setHgap(10);
       
       Image image = new Image(getClass().getResourceAsStream("/res/userpanel.jpg"),30,35,true,true);
       ImageView view = new ImageView(image);
       
       Label heading = new Label("User Panel");
       heading.setGraphic(view);
       GridPane.setConstraints(heading, 0, 0);
       
       Button shopping = new Button("Shopping Menu");
       shopping.setMaxWidth(200);
       shopping.setMinWidth(105);
       GridPane.setConstraints(shopping, 0, 3);
       
       Button manageacc = new Button("Manage Account");
       manageacc.setMaxWidth(200);
       manageacc.setMinWidth(105);
       GridPane.setConstraints(manageacc, 0, 5);
       
       Button history = new Button("View History");
       history.setMaxWidth(200);
       history.setMinWidth(105);
       GridPane.setConstraints(history, 0, 7);
       
       Button logout = new Button("Log Out");
       logout.setMaxWidth(200);
       logout.setMinWidth(105);
       GridPane.setConstraints(logout, 8, 0);
   
       grid.getChildren().addAll(heading,shopping,manageacc,history,logout);
       Scene scene = new Scene(grid,330,210);
       scene.getStylesheets().add("Style.css");
       window.setScene(scene);
       window.show();
       
       shopping.setOnAction(e ->// shoppingmenu(scene)
       {
           ShoppingMenu shop = new ShoppingMenu();
           try {
               shop.start(window);
           } catch (InterruptedException ex) {
               Logger.getLogger(UserAccount.class.getName()).log(Level.SEVERE, null, ex);
           }
           shop.displayShoppingMenu(scene, username);
           
       }
       );
       
       manageacc.setOnAction(e ->{
       
           
           displayManageAcc(username, scene);
           
           
       });
       
       history.setOnAction(e ->{
           
           History h = new History();
           try {
               h.start(window);
           } catch (InterruptedException ex) {
               Logger.getLogger(UserAccount.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               h.displayHistory(username, scene);
           } catch (IOException ex) {
               Logger.getLogger(UserAccount.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       });
  
       logout.setOnAction(e -> {
           window.setTitle("***Easy Shopping System***");
           window.setScene(back);
       });
    }
   
   public void displayManageAcc(String username, Scene back)
    {
        GridPane layout1 = new GridPane();
        layout1.setPadding(new Insets(10, 10, 10, 10));
        layout1.setVgap(10);
        layout1.setHgap(10);
        
        Label heading1 = new Label("Account Settings");
        Image image = new Image(getClass().getResourceAsStream("/res/settings.jpg"),35,30,true,true);
        heading1.setGraphic(new ImageView(image));
        GridPane.setConstraints(heading1, 6, 0);
        layout1.getChildren().add(heading1);
        
        GridPane layout2 = new GridPane();
        layout2.setPadding(new Insets(10, 10, 10, 10));
        layout2.setVgap(10);
        layout2.setHgap(10);
        
        Label usernamee = new Label("Username : " + username);
        GridPane.setConstraints(usernamee, 0, 0);
        
        PasswordField pass = new PasswordField();
        pass.setPromptText("Password");
        pass.setMinWidth(250);
        pass.setMaxWidth(500);
        GridPane.setConstraints(pass, 0, 1);
        
        PasswordField repass = new PasswordField();
        repass.setPromptText("Re-Enter Password");
        repass.setMaxWidth(500);
        repass.setMinWidth(250);
        GridPane.setConstraints(repass, 0, 2);
        
        layout2.getChildren().addAll(usernamee, pass, repass);
        
        GridPane layout3 = new GridPane();
        layout3.setPadding(new Insets(10, 10, 10, 10));
        layout3.setVgap(10);
        layout3.setHgap(10);
        
        Button changePass = new Button("Change Password");
        GridPane.setConstraints(changePass, 10, 0);
        
        Button backk = new Button("Back");
        GridPane.setConstraints(backk, 0, 0);
        layout3.getChildren().addAll(changePass, backk);
        
        BorderPane bp = new BorderPane();
        bp.setTop(layout1);
        bp.setCenter(layout2);
        bp.setBottom(layout3);
        
        Scene scene = new Scene(bp, 280, 200);
        scene.getStylesheets().add("Style.css");
        window.setScene(scene);
        window.setTitle("Account Settings");
        window.show();
        
        
        changePass.setOnAction(e ->{
           String passChk, repassChk;
           passChk = pass.getText();
           repassChk = repass.getText();
           changePassChecker(username,passChk, repassChk, back);
        });
        
        backk.setOnAction(e ->{
           window.setTitle("User Panel");
           window.setScene(back);
        });
 
    }
    
    public void changePassChecker(String username, String pass, String repass, Scene back)
    {
        if(pass.equals("") || repass.equals(""))
        {
            Alert.display("Error", "Password Field Is Empty");
        }
        else
       if(!pass.equals(repass))
            {
                Alert.display("Error", "Password Not Matched");
            }
        else
            {
                LoginChecker chk = new LoginChecker();
                chk.userSettings(username, pass, userFile, userPass, userPassPath);
                window.setScene(back);
                window.setTitle("User Panel");
                
            }
    }
    
    
    
    

    @Override
    public void displayPanel(Scene main) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
