/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiPackage;

import guiPackage.CheckOutAndHistoryReader;
import guiPackage.Start;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import EasyShoppingSystem.myArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author zainm
 */
public class History extends Start
{
    String path1 = "files\\History\\";
    String path3 = ".txt";
    
    File f;
    FileWriter fw;
    PrintWriter pw;
    FileReader fr;
    Scanner sc;
    
    TableView table = new TableView();
    myArrayList<String> item = new myArrayList<>();
    myArrayList<Double> price = new myArrayList<>();
    
    public void displayHistory(String username, Scene back) throws IOException
    {
        window.setTitle("User History");
        
       GridPane layout1 = new GridPane();
       layout1.setPadding(new Insets(10,10,10,10));
       layout1.setVgap(10);
       layout1.setHgap(10);
        
       Label heading = new Label("User History");
       heading.getStyleClass().add("label-checkout");
       Image image = new Image(getClass().getResourceAsStream("/res/history.png"),35,35,true,true);
       heading.setGraphic(new ImageView(image));
       GridPane.setConstraints(heading, 11, 0);
       layout1.getChildren().add(heading);
       
       
       GridPane layout2 = new GridPane();
       layout2.setPadding(new Insets(10,10,10,10));
       layout2.setVgap(10);
       layout2.setHgap(10);
       
       TableColumn<CheckOutAndHistoryReader, String> nameColumn = new TableColumn<>("Items");
       nameColumn.setMinWidth(200);
       nameColumn.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
     
       TableColumn<CheckOutAndHistoryReader, Double> priceColumn = new TableColumn<>("Price");
       priceColumn.setMinWidth(200);
       priceColumn.setCellValueFactory(new PropertyValueFactory<>("ItemPrice"));
     /*  
       TableColumn<CheckOutAndHistoryReader, String> dateColum = new TableColumn<>("Date");
       dateColum.setMinWidth(150);
       dateColum.setCellValueFactory(new PropertyValueFactory<>("date"));
       */
       
      
       table = new TableView();
       table.getColumns().addAll(nameColumn, priceColumn);//, dateColum);
       table.setMaxHeight(150);
       GridPane.setConstraints(table, 0, 0);
       
       layout2.getChildren().add(table);
       
       
       GridPane layout3 = new GridPane();
       layout3.setPadding(new Insets(10,10,10,10));
       layout3.setVgap(10);
       layout3.setHgap(10);
       
       Button backk = new Button("Goto User Panel");
       GridPane.setConstraints(backk, 14, 0);
       layout3.getChildren().add(backk);
       
       BorderPane bp = new BorderPane();
       bp.setTop(layout1);
       bp.setCenter(layout2);
       bp.setBottom(layout3);
       
       Scene scene = new Scene(bp, 400, 300);
       scene.getStylesheets().add("Style.css");
       window.setScene(scene);
       window.show();
   
       table.getItems().addAll(showHistory(username));
       
       backk.setOnAction(e ->{
          
           window.setTitle("User Panel");
           window.setScene(back);
           
       });
    }
    
    public ObservableList<CheckOutAndHistoryReader> showHistory(String filename) throws IOException
    {
        
        String path1 = "files//History//";
        String path3 = ".txt";
        String path = path1 + filename + path3;
        
        CheckOutAndHistoryReader h= new CheckOutAndHistoryReader();
        h.getDataFromFile(path);
        int i = h.lineNum(path);
      /*  
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String datee = dateFormat.format(date);
      */
      
        ObservableList<CheckOutAndHistoryReader> hist = FXCollections.observableArrayList();
        
            try{
        for (int j = 0; j < i; j++) {
            hist.add(new CheckOutAndHistoryReader(h.printN(j), h.printP(j)/*, dateFormat.format(date)*/ ));
        }
    }
            catch (Exception ex){
                
            }
        
        return hist;
    }
    
    public void addHistory(String fileName, String name, double price)
    {
        
        try
        {
           f = new File(path1 + fileName + path3);
           
           if(!f.exists())
           {
               f.createNewFile();
           }
           
           fw = new FileWriter(f, true);
           pw = new PrintWriter(fw, true);
           
           pw.print(name);
           pw.print(" ");
           pw.print(price);
           pw.println();
           
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void delHistory(String fileName, String name)
    {
        String tempName;
        double tempPrice;
        
        try
        {
            sc = new Scanner(new File(path1 + fileName +path3));
            
            while (sc.hasNext())
            {
               tempName = sc.next();
               item.add(tempName);
            
               tempPrice = sc.nextDouble();
               price.add(tempPrice);
            }
            
            for (int i = 0; i < item.getSize(); i++) 
            {
                if(name.equals(item.get(i)))
                {
                    item.remove(i);
                    price.remove(i); 
    
                }
                
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        editHistory(fileName);
    }
    
    public void editHistory(String fileName)
    {    
     try
     {
           
        f = new File(path1 + fileName + path3);
        
         fw = new FileWriter(f);
         pw = new PrintWriter(fw);
         pw.print("");
    
        for (int i = 0; i < item.getSize(); i++) {
            
            fw = new FileWriter(f, true);
            pw = new PrintWriter(fw, true);
            
            pw.print(item.get(i));
            pw.print(" ");
            pw.print(price.get(i));
            pw.println();
        }
        }catch(Exception ee)
        {
            
        }
     
     
        pw.close();
    }
    
    public void clearHistory(String username, ArrayList<String> tempItems, ArrayList<Double> tempPrice){
        String path = "files//History//" + username + ".txt";
//        for (int i = 0; i < tempItems.size(); i++) {
//            tempItems.remove(i);
//            tempPrice.remove(i);
//        }
        tempPrice.clear();
        tempItems.clear();
    }
}
