/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiPackage;

import guiPackage.Start;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import EasyShoppingSystem.myArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import prompt.Alert;

/**
 *
 * @author zainm
 */
public class CheckOutAndHistoryReader extends Start
{
    private String ItemName;
    private double ItemPrice;
  //  private String date;
    myArrayList<String> items = new myArrayList<>();
    myArrayList<Double> prices = new myArrayList<>();
    
    public CheckOutAndHistoryReader()
    {
        this.ItemName = "";
        this.ItemPrice = 0;
      //  this.date = "";
    }
    
    public CheckOutAndHistoryReader(String name, double price)
    {
        this.ItemName = name;
        this.ItemPrice = price;
      //  this.date = date;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public double getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(double ItemPrice) {
        this.ItemPrice = ItemPrice;
    }
    
    public void getDataFromFile(String path)
    {
        
        String name;
        double price;
        Scanner sc;
        
        try
        {
            
           File f = new File(path);
             if(!f.exists())
                {
                    f.createNewFile();
                }
             
            sc = new Scanner(f);
            while(sc.hasNext())
            {
                name = sc.next();
                items.add(name);
                
                price = sc.nextDouble();
                prices.add(price);   
                
            }
            sc.close();
        }catch (Exception e)
        {
            System.out.println("Error in reading history");
        }
        
    }
    
     
     public String printN(int i)
     {
         return items.get(i);
     }
     
     public double printP(int i)
     {
         return prices.get(i);
     }
    
//     public int lineNum(String path) 
//     {
//         int ln = 0;
//      try
//      {
//        LineNumberReader lnr = new LineNumberReader(new FileReader(path));
//        lnr.skip(Long.MAX_VALUE);
//        ln = lnr.getLineNumber()+1;
//        lnr.close();
//        
//      }catch (Exception e)
//      {
//          System.out.println("Error in line method in checkout class");
//      }
//      return ln;
//    }


     public int lineNum(String path) throws FileNotFoundException, IOException{
        LineNumberReader lnr = new LineNumberReader(new FileReader(path));
        lnr.skip(Long.MAX_VALUE);
        int ln = lnr.getLineNumber()+1;
        lnr.close();
       // System.out.println(ln);
        return ln;
    }
    public void displayCheckOut(TableView table2, ListView listview2, Scene backScene, String username, double sum, ArrayList<String> tempItems, ArrayList<Double> tempPrice)
    {
        
        if (tempItems.isEmpty() && tempPrice.isEmpty()) {
            Alert.display("","You Haven't Purchased Any Item!");
        }
        else{
            window.setTitle("Cutomer Receipt");
        
       BorderPane finalLayout = new BorderPane();
       GridPane layout1 = new GridPane();
       layout1.setPadding(new Insets(10,10,10,10));
       layout1.setHgap(10);
       layout1.setVgap(10); 
       Label heading = new Label("Thank You For Shopping Here");
       Image image = new Image(getClass().getResourceAsStream("/res/checkout.png"),35,35,true,true);
       heading.setGraphic(new ImageView(image));
       heading.getStyleClass().add("label-checkout");
       
       GridPane.setConstraints(heading, 1, 0);
       layout1.getChildren().add(heading);
       
       GridPane layout2 = new GridPane();
       layout2.setPadding(new Insets(10,10,10,10));
       layout2.setHgap(10);
       layout2.setVgap(10);
       table2.setMaxHeight(150);
       GridPane.setConstraints(table2, 0, 0);
       layout2.getChildren().addAll(table2);
       
       GridPane layout3 = new GridPane();
       layout3.setPadding(new Insets(10,10,10,10));
       layout3.setHgap(10);
       layout3.setVgap(10);
       
       Label Total = new Label("Total");
       GridPane.setConstraints(Total, 0, 2);
       
       Button back = new Button("<< Goto User Panel");
       GridPane.setConstraints(back, 17, 3);
       GridPane.setConstraints(listview2, 0, 3);
       layout3.getChildren().addAll(Total, listview2, back);
       
       finalLayout.setTop(layout1);
       finalLayout.setCenter(layout2);
       finalLayout.setBottom(layout3);
       
       Scene scene = new Scene(finalLayout, 400, 300);
       scene.getStylesheets().add("Style.css");
       window.setScene(scene);
       window.show();
       
       back.setOnAction(e ->{
           window.setTitle("User Panel");
           window.setScene(backScene);
       });
      saveData(sum, username, tempItems, tempPrice);
        }
        
    }
    /*
    public void saveData(String path2, ObservableList a )
    {
        File history;
        FileWriter fw;
        BufferedWriter bw;
        PrintWriter pw;
        String path1 = "files//History//";
        String path3 = ".txt";
        
        
        try
        {
            history = new File(path1 + path2 + path3);
            if(!history.exists())
            {
                history.createNewFile();
            }
            fw = new FileWriter(history, true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(fw);
            
            pw.print(a);
            pw.println();
            pw.println();
            
            
            pw.close();
            
        } catch ( Exception e)
        {
            e.printStackTrace();
        }
        
        
    }
    */
    public void saveData(double sum, String username,ArrayList<String> tempItems, ArrayList<Double> tempPrice)
    {
        File history;
        FileWriter fw;
        PrintWriter pw;
        String path1 = "files//History//";
        String path3 = ".txt";
        String path = path1 + username + path3;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        Date time = new Date();
//        String curDate = date.toString();
        try
        {
            history = new File(path);
            if(!history.exists())
            {
                history.createNewFile();
            }
            fw = new FileWriter(history, true);
            pw = new PrintWriter(fw, true);
            
            for (int i = 0; i < tempItems.size(); i++) {
                pw.print(tempItems.get(i));
                pw.print(" ");
                pw.println(tempPrice.get(i));
            }
            
            pw.print("Total");
            pw.print(" ");
            pw.print(sum);
            pw.println();
            pw.print(timeFormat.format(time));
            pw.print(" ");
            pw.print(0);
            pw.println();
            pw.print(dateFormat.format(date));
            pw.print(" ");
            pw.println(0);
            
            
            pw.close();
            
        } catch ( Exception e)
        {
            e.printStackTrace();
        }
        
        
        
    }
    
    public void readData()
    {
        String n;
        String p;
        Scanner sc;
        myArrayList<String> name= new myArrayList<>();
        myArrayList<Double> price = new myArrayList<>();
        
        try{
            sc = new Scanner(new File("files//History//zz.txt"));
            while (sc.hasNext())
            {
                n = sc.next();
                name.add(n);
                
                p = sc.next();
                name.add(p);
                
                System.out.println(n);
                System.out.println(p);
                
            }
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
