/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accountHandler;

import itemsHandler.Products;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import prompt.Alert;
import EasyShoppingSystem.Proj;
import EasyShoppingSystem.myArrayList;

/**
 *
 * @author zainm
 */
public class AdminAccount extends Accounts
{
    String choice;
    String heading;
    TableView<Products> table;
    ListView<String> list;
    
    
    ArrayList<String> iN = new ArrayList<>();
    ArrayList<Double> iP = new ArrayList<>();
    ArrayList<Integer> iQ = new ArrayList<>();
    public  void setScene()
    {
        
        window.setTitle("Admin account");
        
        adminpan.setPadding(new Insets(10,10,10,10));
        adminpan.setVgap(8);
        adminpan.setHgap(10);
        
        headingg = new Label("Admin Login");
        Image image = new Image(getClass().getResourceAsStream("/res/lock.png"),35,40,true,true);
        headingg.setGraphic(new ImageView(image));
        headingg.setTextFill(Color.web("#0076a3"));
        GridPane.setConstraints(headingg, 1, 0);
        
        username = new Label("Username");
        GridPane.setConstraints(username, 0, 1);
        
        name = new TextField("forzh");
        GridPane.setConstraints(name, 1, 1);
        
        pass = new Label("Password");
        GridPane.setConstraints(pass, 0, 2);
        
        pas = new PasswordField();
        pas.setPromptText("Password");
        GridPane.setConstraints(pas, 1, 2);
        
        login = new Button("Login");
        GridPane.setConstraints(login, 1, 4);
        
        back = new Button("Back");
        GridPane.setConstraints(back, 2, 4);
  
        adminpan.getChildren().addAll(headingg,username,name,pass,pas,login,back);
        
        adminn = new Scene(adminpan,300,150);
        adminn.getStylesheets().add("Style.css");
        window.setScene(adminn); 
    }
    
    
    public void displayPanel(Scene back, String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public void setButtonsAction(Scene main)
    {
         back.setOnAction(y -> {
            
            window.setScene(main);
            window.setTitle("***Easy Shopping System***");
                });
         
          
        login.setOnAction(e ->{
            
          // boolean flag, error=true;
         
            LoginChecker lc = new LoginChecker(name.getText(),pas.getText());
              int val = 0;
               try 
               {
                   val = lc.adminLogIn(name.getText(), pas.getText());
               } catch (IOException ex) 
               {
                   Logger.getLogger(Proj.class.getName()).log(Level.SEVERE, null, ex);
               }
                   if(val == 0)
                   {
                      // error = false;
                       //adminpanel(main);
                       displayPanel(main);
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
    }
     public void displayPanel(Scene main)
    {
       GridPane grid = new GridPane();
       grid.setPadding(new Insets(10,10,10,10));
       grid.setVgap(20);
       grid.setHgap(8);
       
       Label lbadminpan = new Label("Admin Panel");
       Image image = new Image(getClass().getResourceAsStream("/res/adminpanel.jpg"),35,40,true,true);
       lbadminpan.setGraphic(new ImageView(image));
       GridPane.setConstraints(lbadminpan, 0, 0);
       
       //Label lbl1 = new Label("Administrator Panel");
      // lbl1.setFont(new Font("Arial", 15));
     //  GridPane.setConstraints(lbl1, 2, 1);
       
       Button viewuser = new Button("View Users");
       viewuser.setMaxWidth(200);
       viewuser.setMinWidth(105);
       GridPane.setConstraints(viewuser, 0, 1);
       
       Button itemsmenu = new Button("Manage Items");
       itemsmenu.setMaxWidth(200);
       itemsmenu.setMinWidth(105);
       GridPane.setConstraints(itemsmenu, 0, 2);
       
       Button editaccount = new Button("Manage Account");
       editaccount.setMaxWidth(200);
       editaccount.setMinWidth(105);
       GridPane.setConstraints(editaccount, 0, 3);
       
       Button logout = new Button("Log Out");
       logout.setMaxWidth(200);
       logout.setMinWidth(105);
       GridPane.setConstraints(logout, 10, 0);
       
      // Button mainmenu = new Button("<< Goto main menu ");
       //mainmenu.setMaxWidth(100);
      // GridPane.setConstraints(mainmenu, 3, 7);
           
       grid.getChildren().addAll(lbadminpan, viewuser, itemsmenu, editaccount, logout);
       Scene adminpanelscene = new Scene(grid, 330, 200);
       adminpanelscene.getStylesheets().add("Style.css");
       window.setScene(adminpanelscene);
       
      // mainmenu.setOnAction(e ->{
      //     window.setScene(back);
      // });
      viewuser.setOnAction(e ->{
          
           displayUser(adminpanelscene);
       });
       itemsmenu.setOnAction(e ->{
         ItemsMenu(adminpanelscene);
       });
       logout.setOnAction(e ->{
          
           window.setTitle("*** Easy Shopping System ***");
           window.setScene(main);
       }
       );
       
       editaccount.setOnAction(e ->{
           displayManageAcc(name.getText(), adminpanelscene);
       });
    }
     
     public void displayUser(Scene back)
    {
        window.setTitle("Users List");
        
        GridPane layout1 = new GridPane();
        layout1.setPadding(new Insets(10, 10, 10, 10));
        layout1.setVgap(10);
        layout1.setHgap(10);
        
        Label userlist = new Label("User List");
        Image image = new Image(getClass().getResourceAsStream("/res/viewuser.jpg"),35,35,true,true);
        userlist.setGraphic(new ImageView(image));
        GridPane.setConstraints(userlist, 0, 0);
        layout1.getChildren().add(userlist);
        
        GridPane layout2 = new GridPane();
        layout2.setPadding(new Insets(10, 10, 10, 10));
        layout2.setVgap(10);
        layout2.setHgap(10);
        
        list = new ListView();
        list.setMaxWidth(300);
        list.setMaxHeight(500);
        GridPane.setConstraints(list, 0, 0);
        layout2.getChildren().add(list);
        
        GridPane layout3 = new GridPane();
        layout3.setPadding(new Insets(10, 10, 10, 10));
        layout3.setVgap(10);
        layout3.setHgap(10);
        
        Button delUser = new Button("Delete User");
        GridPane.setConstraints(delUser, 11, 0);
        
        Button backk = new Button("<< Back");
        GridPane.setConstraints(backk, 0, 0);
        layout3.getChildren().addAll(delUser,backk);
        
        BorderPane layout = new BorderPane();
        layout.setTop(layout1);
        layout.setCenter(layout2);
        layout.setBottom(layout3);
        
        
        Scene scene = new Scene(layout, 280, 460);
        scene.getStylesheets().add("Style.css");
        window.setScene(scene);
        window.show();
        list.getItems().addAll(getUsers());
        
        delUser.setOnAction(e -> {
          try
          {
           String name = list.getSelectionModel().getSelectedItem();
           delSelectedUser(name);
           list.getItems().clear();
           list.getItems().addAll(getUsers());
          }catch(Exception ee)
          {
              Alert.display("Error", "No User Selected From List");
          }
         
        });
        backk.setOnAction(e ->{
          
            window.setTitle("Admin Panel");
            window.setScene(back);
            
        });
        
    }
     
     public void delSelectedUser(String item) throws IOException
    {
        File unF = new File("files//User//usernames.txt"); 
        File upF = new File("files//User//passwords.txt"); 
        File file = new File("files//History//" + item + ".txt");

        
        
        ArrayList<String> uName = new ArrayList<>();
        ArrayList<String> uPass = new ArrayList<>();
        
        String uN;
        String uP;
        Scanner sc;
        try
        {
             sc = new Scanner(unF);
             while(sc.hasNext())
             {
                uN = sc.next();
                uName.add(uN); 
             }
             
             sc = new Scanner(upF);
             while (sc.hasNext())
             {
                 uP = sc.next();
                 uPass.add(uP);
             }
        }catch (Exception e)
        {
            System.out.println("Error deleting user");
        }
        removeUser(item, uName, uPass, unF, upF,file);
    }
    
    public void removeUser(String item, ArrayList username, ArrayList userpass, File userFile, File userPass, File file) throws IOException
    {
        FileWriter fw;
        PrintWriter pw;
        String path = file.getCanonicalPath();
        File filePath = new File(path);
        if(!filePath.exists()){
            filePath.createNewFile();
        }
            filePath.delete();
            filePath = null;    
            Alert.display("Alert", "Deleted Successfully!");
        
        for (int i = 0; i < username.size(); i++) 
        {
            if(item.equals(username.get(i)))
            {
                username.remove(i);
                userpass.remove(i);
            }
            
        }
            try
            {
                fw = new FileWriter(userFile);
                pw = new PrintWriter(fw);
                
                pw.print("");
                
                pw.close();
                
                fw = new FileWriter(userFile, true);
                pw = new PrintWriter(fw, true);
                
                for (int i = 0; i < username.size(); i++)
                {
                    
                    pw.print(username.get(i));
                    pw.println();
                }
                
                pw.close();
                
                fw = new FileWriter(userPass);
                pw = new PrintWriter(fw);
                
                for (int i = 0; i < userpass.size(); i++)
                {
                    
                    pw.print(userpass.get(i));
                    pw.println();
                }
                
                pw.close();
            }catch(Exception e)
            {
                System.out.println("Error editing file");
            }
           
              
        
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
                chk.userSettings(username, pass, adminFile, adminPass, adminPassPath);
                window.setScene(back);
                window.setTitle("User Panel");
                
            }
    }
    
    public ArrayList<String> getUsers()
    {
        ArrayList<String> username = new ArrayList<>();
        Scanner sc;
        File f = new File("files//User//usernames.txt");
        String tempU;
        try
        {
            if(!f.exists())
            {
                f.createNewFile();
            }
            sc = new Scanner(f);
            while (sc.hasNext())
            {
                tempU = sc.next();
                username.add(tempU);
            }
        }catch (Exception e)
        {
            System.out.println("Error reading usernames");
        }
        String str,str1;
        int result;
        for (int i = 0; i < username.size(); i++)  
        {  
            int index = i;  
            for (int j = i + 1; j < username.size(); j++){
                str=(String) username.get(j);
                str1= (String) username.get(i);       
                if ((result=str.compareToIgnoreCase(str1))<0){  
                    index = j;//searching for lowest index  
                }  
            }  
            String smallerNumber =(String)username.get(index); 
            String str2=(String)username.get(i);
            username.set(index, str2);  
            username.set(i, smallerNumber);  
        }
        return username;
    }
    public void ItemsMenu(Scene back)
    {
 
       window.setTitle("Items Menu");
       GridPane grid1 = new GridPane();
       grid1.setPadding(new Insets(10,10,10,10));
       grid1.setVgap(10);
       grid1.setHgap(10);
       Label choose = new Label("Manage Items");
       choose.getStyleClass().add("label-checkout");
       Image image = new Image(getClass().getResourceAsStream("/res/itemsmenu.jpg"),35,35,true,true);
       choose.setGraphic(new ImageView(image));
       GridPane.setConstraints(choose, 0, 0);
       
       Button backk = new Button("<< Back");
       backk.setMaxWidth(200);
       backk.setMinWidth(105);
       GridPane.setConstraints(backk, 28, 0);
       
       grid1.getChildren().addAll(choose, backk);
       
       GridPane grid2 = new GridPane();
       grid2.setPadding(new Insets(10,10,10,10));
       grid2.setVgap(10);
       grid2.setHgap(10);
       
       Button clothes = new Button("Clothes");
       clothes.setMaxWidth(200);
       clothes.setMinWidth(105);
       GridPane.setConstraints(clothes, 0, 4);
       
       Button appliances = new Button("Electronic Appliances");
       appliances.setMaxWidth(200);
       appliances.setMinWidth(105);
       GridPane.setConstraints(appliances, 0, 7);
       
       Button books = new Button("Books");
       books.setMaxWidth(200);
       books.setMinWidth(105);
       GridPane.setConstraints(books, 0, 10);
       
       Button medicine = new Button("Medicine");
       medicine.setMaxWidth(200);
       medicine.setMinWidth(105);
       GridPane.setConstraints(medicine, 0, 13);
       
       grid2.getChildren().addAll(clothes, appliances, books, medicine);
       
       
       GridPane grid3 = new GridPane();
       grid3.setPadding(new Insets(10,10,10,10));
       grid3.setVgap(8);
       grid3.setHgap(10);
       
       Label headingChoice = new Label("No Category Selected");
       headingChoice.getStyleClass().add("label-cart-total");
       GridPane.setConstraints(headingChoice, 0, 1);
       
       TableColumn<Products, String> nameColumn = new TableColumn<>("Items");
       nameColumn.setMinWidth(200);
       nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
     
       TableColumn<Products, Double> priceColumn = new TableColumn<>("Price");
       priceColumn.setMinWidth(100);
       priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
       
       TableColumn<Products, Integer> quantityColumn = new TableColumn<>("Stock");
       quantityColumn.setMinWidth(100);
       quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
  
       table = new TableView();
       table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);
       table.setMaxHeight(200);
       GridPane.setConstraints(table, 0, 2);
       grid3.getChildren().addAll(headingChoice,table);
       
       GridPane grid4 = new GridPane();
       grid4.setPadding(new Insets(10,10,10,10));
       grid4.setVgap(10);
       grid4.setHgap(10);
       
       TextField itemName = new TextField();
       itemName.setPromptText("Item name");
       itemName.setMaxWidth(200);
       GridPane.setConstraints(itemName, 0, 0);
       
       TextField itemPrice = new TextField();
       itemPrice.setPromptText("Item price");
       itemPrice.setMaxWidth(100);
       GridPane.setConstraints(itemPrice, 2, 0);
       
       TextField itemQuantity = new TextField();
       itemQuantity.setPromptText("Item quantity");
       itemQuantity.setMaxWidth(100);
       GridPane.setConstraints(itemQuantity, 4, 0);
       
       Button addItem = new Button("Add Item");
       GridPane.setConstraints(addItem, 6, 0);
       
       Button delItem = new Button("Delete Item");
       GridPane.setConstraints(delItem, 8, 0);
       grid4.getChildren().addAll(itemName, itemPrice, itemQuantity, addItem, delItem);
       
       BorderPane layout = new BorderPane();
       layout.setTop(grid1);
       layout.setCenter(grid2);
       layout.setRight(grid3);
       layout.setBottom(grid4);
       
       Scene scene = new Scene(layout, 600, 360);
       scene.getStylesheets().add("Style.css");
       window.setScene(scene);
       window.show();
       
       //button action
       
       backk.setOnAction(e ->{
         
         choice = "";
         window.setTitle("Admin Panel");
         window.setScene(back);
       });
       clothes.setOnAction(e ->{
         
               heading = "Clothes";
               headingChoice.setText(heading);
               choice = "Clothes.txt";
               table.getItems().clear();
               String path = "files//Items//Clothes.txt";
               table.getItems().addAll(getProducts(path));
               
           
       });
       
       appliances.setOnAction(e -> {
          
               heading = "Appliances";
               headingChoice.setText(heading);
               choice = "Appliances.txt";
               table.getItems().clear();
               String path = "files//Items//Appliances.txt";
               table.getItems().addAll(getProducts(path));
              
           
           
       });
       
       books.setOnAction(e ->{
            
               heading = "Books";
               headingChoice.setText(heading);
               choice = "Books.txt";
               table.getItems().clear();
               String path = "files//Items//Books.txt";
               table.getItems().addAll(getProducts(path));
               
           
       });
       
       medicine.setOnAction(e -> {
         
               heading = "Medicine";
               headingChoice.setText(heading);
               choice = "Medicine.txt";
               table.getItems().clear();
               String path = "files//Items//Medicine.txt";
               table.getItems().addAll(getProducts(path));
               
           
       });
       
       addItem.setOnAction(e ->{
        try
        {
            if(itemName.getText().equals("") ||itemPrice.getText().equals("") || itemQuantity.getText().equals("") )
            {
                Alert.display("Error", "Please Fill All The Fields");
            }
            else
            {
                 if(checkItemName(itemName.getText()) && checkItemPrice(itemPrice.getText()) && checkItemQuantity(itemQuantity.getText()))
                 {
                     addToList(choice, itemName.getText(), itemPrice.getText(), itemQuantity.getText());
          
                     itemName.clear();
                     itemPrice.clear();
                     itemQuantity.clear();
                 }
                 
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error in button addItem");
        }
       });
       
       delItem.setOnAction(e -> {
       try
       {
          String name = table.getSelectionModel().getSelectedItem().getName();
          double price = table.getSelectionModel().getSelectedItem().getPrice();
          int quantity = table.getSelectionModel().getSelectedItem().getQuantity();
          
          delFromFile(choice, name, price, quantity);
          delFromTable();
       }
       catch(Exception ee)
       {
           Alert.display("Error", "Please Selecet An Item From The List");
       }
       });
       
    }
    
     
   public ObservableList<Products> getProducts(String path)
   {
       Products p = new Products();
       p.getDataaFromFile(path);
       int i = p.lineNum(path);
       
       ObservableList<Products> products = FXCollections.observableArrayList();
       for (int j = 0; j < i ; j++) 
       {
         try
         {
              products.add( new Products(p.printN(j), p.printP(j), p.printQ(j)));
         }
         catch (Exception e)
         {
             System.out.println("Error in getting Items!");
            // Alert.display("Error", "File Is Empty");
         }
 
       }
       
       return products;       
   }
   
   public boolean checkItemName(String item)
   {
       boolean result = true;
       
       try
       {
           String itemName = item;
       }catch(Exception eee)
       {
           Alert.display("Error", "Item name is not valid");
           result = false;
       }
 
       return result;
   }
   
   public boolean checkItemPrice(String price)
   {
       boolean result = true;
        try
       {
           double itemPrice = Double.parseDouble(price);
           
       }catch(Exception eee)
       {
           Alert.display("Error", "Item price is not valid");
           result = false;
       }
 
       return result;
   }
   
   public boolean checkItemQuantity(String quantity)
   {
       boolean result = true;
        
       try
       {
          int itemQuan = Integer.parseInt(quantity);
          
       }catch(Exception eee)
       {
           Alert.display("Error", "Item quantity is not valid");
           result = false;
       }
       
       return result;
   }
   
   public void addToList(String path, String name, String price, String quantity)
   {
       boolean selected = true;
       String fPath = "files//Items//"+ path;
       File f = new File(fPath);
//       FileWriter fw;
//       PrintWriter pw;
        String tempVariable;
       String print = name + " " + price + " " + quantity;
       try
       {
          Scanner sc = new Scanner(f);
//           fw = new FileWriter(f, true);
//           pw = new PrintWriter(fw, true);
        ArrayList<String> tempAL = new ArrayList<String>();
        while(sc.hasNext()){
            tempVariable = sc.nextLine();
            tempAL.add(tempVariable);
        }
        tempAL.add(print);
        selectionSort(tempAL, fPath);

//               pw.print(name);
//               pw.print(" ");
//               pw.print(price);
//               pw.print(" ");
//               pw.println(quantity);
                tempAL.clear();
               sc.close();
//               fw.close();
//               pw.close();
           
       }catch(Exception e)
       {
           Alert.display("Error", "Please Choose Category");
          // table.getItems().clear();
          // System.out.println("Error adding items");
          selected = false;
       }
       
       if(selected)
       {
           Products p = new Products();
           p.setName(name);
           p.setPrice(Double.parseDouble(price));
           p.setQuantity(Integer.parseInt(quantity));
           table.getItems().add(p);
       }
   
   }
   
   public static void selectionSort(ArrayList<String> arr, String path) throws IOException{
       String str,str1;
        int result;
        FileWriter tempFW = new FileWriter(path);
           PrintWriter tempPW = new PrintWriter(tempFW);
        for (int i = 0; i < arr.size(); i++)  
        {  
            int index = i;  
            for (int j = i + 1; j < arr.size(); j++){
                str=(String) arr.get(j);
                str1= (String) arr.get(i);       
                if ((result=str.compareToIgnoreCase(str1))<0){  
                    index = j;//searching for lowest index  
                }  
            }  
            String smallerNumber =(String)arr.get(index); 
            String str2=(String)arr.get(i);
            arr.set(index, str2);  
            arr.set(i, smallerNumber);  
        }
        for (int i = 0; i < arr.size(); i++) {
           
           tempPW.println(arr.get(i));
       }
        arr.clear();
        tempFW.close();
        tempPW.close();
   }
   public void delFromFile(String path, String name, double price, int quantity)
   { 
       String tempN;
       double tempP;
       int tempQ;
       
       String fPath = "files//Items//"+ path;
       File f = new File(fPath);
       Scanner sc;
       
       try
       {
           sc = new Scanner(f);
           while(sc.hasNext())
           {
               tempN = sc.next();
               iN.add(tempN);
               
               tempP = sc.nextDouble();
               iP.add(tempP);
               
               tempQ = sc.nextInt();
               iQ.add(tempQ);
               
           }
//            LoginChecker lc = new LoginChecker();
//            for (int i = 0; i < lc.lineNum(fPath); i++) {
//               tempN =(String) sc.next();
//               iN.add(tempN);
//               
//               tempP =(Double) sc.nextDouble();
//               iP.add(tempP);
//               
//               tempQ = (Integer) sc.nextInt();
//               iQ.add(tempQ);
//           }
           sc.close();
           for (int i = 0; i < iN.size(); i++) {
               
               if(name.equals(iN.get(i)))
               {
                   iN.remove(name);
                   iP.remove(price);
                   iQ.remove(quantity);
                   break;
               }
           }
           
       }catch(Exception e)
       {
//           System.out.println("Error deleting items");
       }
       editFile(fPath);
   }
   
   public void editFile(String path)
   {
       File f = new File(path);
       FileWriter fw;
       PrintWriter pw;
       try
       {
 
           fw = new FileWriter(f);
           pw = new PrintWriter(fw);
           pw.print("");
           
           fw = new FileWriter(f, true);
           pw = new PrintWriter(fw, true);
           
           for (int i = 0; i < iN.size(); i++) {
               
               pw.print(iN.get(i));
               pw.print(" ");
               pw.print(iP.get(i));
               pw.print(" ");
               pw.println(iQ.get(i));
    
           }
           fw.close();
           pw.close();
           iN.clear();
           iP.clear();
           iQ.clear();
       }catch(Exception e)
       {
           System.out.println("Error editing file");
       }
   }
   
   public void delFromTable()
   {
       ObservableList<Products> allItems, selectedItems;
       
       allItems = table.getItems();
       selectedItems = table.getSelectionModel().getSelectedItems();
       selectedItems.forEach(allItems :: remove);

   }
    
    
    
    
}
