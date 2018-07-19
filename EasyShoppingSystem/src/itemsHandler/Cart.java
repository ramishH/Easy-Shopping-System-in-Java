/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 *
 * @author zainm
 */
public class Cart extends Products
{
    private String name;
    private double price;
    
    public Cart()
    {
        this.name = "";
        this.price = 0;
    }
    
    public Cart(String name, double price)
    {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public String toString()
    {
        return "\n" + this.name + " " + this.price;
    }
   /* 
    public ObservableList<Cart> addToCart(TableView table, String name, double price)
    {
       
        
        ObservableList<Cart> carts = FXCollections.observableArrayList();
        carts.add(new Cart(name, price));
        
        
    }*/
}
