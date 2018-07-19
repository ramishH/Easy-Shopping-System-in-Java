/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiPackage;

import guiPackage.Start;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import prompt.Alert;
import itemsHandler.Cart;
import itemsHandler.Products;
import java.util.ArrayList;
import EasyShoppingSystem.myArrayList;

/**
 *
 * @author zainm
 */
public class ShoppingMenu extends Start
{
    private int counter = 1;
    private double sum = 0;
    ListView<String> listview; 
    ListView<Double> listview2;
    TableView<Products> table;
    TableView<Cart> table2;
   // static String pathOfItemsFile = "files\\Items\\itemsList.txt";
    ArrayList<String> tempItems = new ArrayList<String>();
    ArrayList<Double> tempPrice = new ArrayList<Double>();
    
    public void displayShoppingMenu(Scene back, String username)
    {
       window.setTitle("Shopping Menu");
       GridPane grid1 = new GridPane();
       grid1.setPadding(new Insets(10,10,10,10));
       grid1.setVgap(2);
       grid1.setHgap(10);
 
       Image image = new Image(getClass().getResourceAsStream("/res/shopping.png"),40,45,true,true);
       ImageView view = new ImageView(image);
       Label heading = new Label("Shopping Menu");
       heading.setGraphic(view);
       heading.getStyleClass().add("label-shopping");
       GridPane.setConstraints(heading, 0, 0);
       
       Button backbtn = new Button("<< Back");
       backbtn.setMaxWidth(200);
       backbtn.setMinWidth(105);
       GridPane.setConstraints(backbtn, 41, 0);
       
       grid1.getChildren().addAll(heading, backbtn);
       
       
       // for buttons
       GridPane grid2 = new GridPane();
       grid2.setPadding(new Insets(10,10,10,10));
       grid2.setVgap(10);
       grid2.setHgap(10);
       
       Label cartOpt = new Label("Cart Options");
       cartOpt.getStyleClass().add("label-cart-total");
       GridPane.setConstraints(cartOpt, 0, 2);
       
       Button add2cart = new Button("Add To Cart");
       add2cart.setMaxWidth(200);
       add2cart.setMinWidth(105);
       GridPane.setConstraints(add2cart, 0, 5);
       
       Button delcart = new Button("Delete From Cart");
       delcart.setMaxWidth(200);
       delcart.setMinWidth(105);
       GridPane.setConstraints(delcart, 0, 8);
       
       Button clrcart = new Button("Clear Cart");
       clrcart.setMaxWidth(200);
       clrcart.setMinWidth(105);
       GridPane.setConstraints(clrcart, 0, 11);
       
       Button chkout = new Button("Check Out");
       chkout.setMaxWidth(200);
       chkout.setMinWidth(105);
       GridPane.setConstraints(chkout, 0, 14);
 
       grid2.getChildren().addAll(cartOpt, add2cart, delcart, clrcart,chkout);
       
       
       // for table view
       GridPane grid3 = new GridPane();
       grid3.setPadding(new Insets(10,10,10,10));
       grid3.setVgap(5);
       grid3.setHgap(10);
  
       Label choiceHeading = new Label("No Category Selected");
       choiceHeading.getStyleClass().add("label-cart-total");
       GridPane.setConstraints(choiceHeading, 1, 3);
       
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
       GridPane.setConstraints(table, 1, 8);
      
       //Cart 2
       TableColumn<Cart, String> nameColumn2 = new TableColumn<>("Items");
       nameColumn2.setMinWidth(200);
       nameColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
     
       TableColumn<Cart, Double> priceColumn2 = new TableColumn<>("Price");
       priceColumn2.setMinWidth(200);
       priceColumn2.setCellValueFactory(new PropertyValueFactory<>("price"));
      
       table2 = new TableView();
       table2.getColumns().addAll(nameColumn2, priceColumn2);
       table2.setMaxHeight(100);
       GridPane.setConstraints(table2, 1, 11);
      
       
       // for cart  
       Label cart = new Label("Cart");
       cart.getStyleClass().add("label-cart-total");
       GridPane.setConstraints(cart, 1, 10);
 
       //for total  
       Label total = new Label("Total");
       total.getStyleClass().add("label-cart-total");
       GridPane.setConstraints(total, 1, 14);
       listview2 = new ListView<>();
       listview2.setMaxWidth(80);
       listview2.setPrefHeight(30);
       listview2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); 
       GridPane.setConstraints(listview2, 1, 15);
       grid3.getChildren().addAll(choiceHeading ,table ,cart, table2, total, listview2);
       
       
       GridPane grid4 = new GridPane();
       grid4.setPadding(new Insets(10,10,10,10));
       grid4.setVgap(10);
       grid4.setHgap(10);
       
       Label categories = new Label("Categories");
       categories.getStyleClass().add("label-cart-total");
       GridPane.setConstraints(categories, 0, 2);
       
       Button clothes = new Button("Clothes");
       clothes.setMaxWidth(200);
       clothes.setMinWidth(105);
       GridPane.setConstraints(clothes, 0, 5);
       
       Button appliances = new Button("Electronic Appliances");
       appliances.setMaxWidth(200);
       appliances.setMinWidth(105);
       GridPane.setConstraints(appliances, 0, 8);
       
       Button books = new Button("Books");
       books.setMaxWidth(200);
       books.setMinWidth(105);
       GridPane.setConstraints(books, 0, 11);
       
       Button medicine = new Button("Medicine");
       medicine.setMaxWidth(200);
       medicine.setMinWidth(105);
       GridPane.setConstraints(medicine, 0, 14);
       grid4.getChildren().addAll(categories,clothes, appliances, books, medicine);
       
       BorderPane layout = new BorderPane();
       layout.setTop(grid1);
       layout.setLeft(grid4);
       layout.setRight(grid2);
       layout.setCenter(grid3);
 
       Scene scene = new Scene(layout, 720, 550);
       scene.getStylesheets().add("Style.css");
       
       window.setScene(scene);
       window.show();
     
      
       clothes.setOnAction(e ->{
          
           String path = "files//Items//Clothes.txt";
           choiceHeading.setText("Clothes");
           table.getItems().clear();
           table.getItems().addAll(getProducts(path));
       });
       
        appliances.setOnAction(e ->{
          
           String path = "files//Items//Appliances.txt";
           choiceHeading.setText("Appliances");
           table.getItems().clear();
           table.getItems().addAll(getProducts(path));
       });
        
         books.setOnAction(e ->{
          
           String path = "files//Items//Books.txt";
           choiceHeading.setText("Books");
           table.getItems().clear();
           table.getItems().addAll(getProducts(path));
       });
         
          medicine.setOnAction(e ->{
          
           String path = "files//Items//Medicine.txt";
           choiceHeading.setText("Medicine");
           table.getItems().clear();
           table.getItems().addAll(getProducts(path));
       });
      
      
       add2cart.setOnAction(e -> {
           try
           {
               listview2.getItems().clear();
               table2.getItems().addAll(addToCart(username));
 
                
           } catch (Exception exx)
           {
               Alert.display("Warning", "Please Make Sure You Select An Item");
           }
          // add2cartt();
       });
       
       delcart.setOnAction(e -> {
        try
         {
             
           listview2.getItems().clear();
           delFromCart(username);
           
         }  catch (Exception ex)
           {
               Alert.display("Error", "Please Select An Item From The Cart");
//               ex.printStackTrace();
           }         
   
         
       });
       
       clrcart.setOnAction(e -> {
           sum = 0;
           History h = new History();
           h.clearHistory(username, tempItems, tempPrice);
           table2.getItems().clear();
           listview2.getItems().clear();
       });
       
       chkout.setOnAction(e ->{
           CheckOutAndHistoryReader chkOut = new CheckOutAndHistoryReader();
           try {
               chkOut.start(window);
           } catch (InterruptedException ex) {
               Logger.getLogger(ShoppingMenu.class.getName()).log(Level.SEVERE, null, ex);
           }
           chkOut.displayCheckOut(table2, listview2,back,username, sum, tempItems, tempPrice);
           
       });
       backbtn.setOnAction(e ->{
           window.setTitle("User Panel");
           window.setScene(back);
           });
         
    }
   
   
   public ObservableList<Products> getProducts(String path)
   {
       Products p = new Products();
       p.getDataaFromFile(path);
       int i = p.lineNum(path);
      
       ObservableList<Products> products = FXCollections.observableArrayList();
       for (int j = 0; j < i; j++) 
       {
          products.add( new Products(p.printN(j), p.printP(j), p.printQ(j)));
       }
       
       return products;       
   }
   
   public ObservableList<Cart> addToCart(String username)
   {
        String name = table.getSelectionModel().getSelectedItem().getName();
        double price = table.getSelectionModel().getSelectedItem().getPrice();
        int quantity = table.getSelectionModel().getSelectedItem().getQuantity();
        quantity = quantity - 1 ;
        
        History history = new History();
//        try {
//            history.start(window);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(ShoppingMenu.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        history.addHistory(username, name, price);
       
       tempItems.add(name);
       tempPrice.add(price);
        
        delFromItemsList();
        
        if(quantity >0)
        {
          table.getItems().addAll(editProducts(name, price, quantity));
        }
        
        ObservableList<Cart> carts = FXCollections.observableArrayList();
        carts.add(new Cart(name, price));
        sum = sum + price;
        listview2.getItems().addAll(sum);

        return carts;   
   }
   
    public ObservableList<Products> editProducts(String item, double price, int quantity)
   {
       
       ObservableList<Products> products = FXCollections.observableArrayList();
       products.add( new Products(item, price, quantity));
       
       
       return products;       
   }
   
   public void delFromCart(String username)
   {
      ObservableList<Cart> allitems, selected;
      
      String name = table2.getSelectionModel().getSelectedItem().getName();
      double price = table2.getSelectionModel().getSelectedItem().getPrice();
      
      History history = new History();
        
//      history.delHistory(username, name);
      
      tempItems.remove(name);
      tempPrice.remove(price);
      
      allitems = table2.getItems();
      selected = table2.getSelectionModel().getSelectedItems();
      selected.forEach(allitems :: remove);
      
      sum = sum - price;
      listview2.getItems().addAll(sum);
      
     // editItemList(name, price);
   }
   
   public void delFromItemsList()
   {
      ObservableList<Products> allItems, selected;
      allItems = table.getItems();
      selected = table.getSelectionModel().getSelectedItems();
      selected.forEach(allItems :: remove); 
   }
   
   public void editItemList(String name,  double price)
   {
       Products p = new Products();
     //  p.getDataaFromFile(pathOfItemsFile);
       int quantity = p.arrayListEditOnDeletion(name);
       table.getItems().addAll(editProducts(name, price, quantity));
   }

}


