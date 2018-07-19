/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsHandler;


import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;

import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import EasyShoppingSystem.myArrayList;

/**
 *
 * @author zainm
 */
public class Products 
{
    private Scanner sc;
    File f;
    File oldF = new File("files\\Items\\itemsList2.txt");
    File newF = new File("files\\Items\\itemsList.txt");
    
    private String name;
    private double price;
    private int quantity;
    
    myArrayList<String> namelist = new myArrayList<>();
    myArrayList<Double> pricelist = new myArrayList<>();
    ArrayList<Integer> quantitylist = new ArrayList<>();
    
    public Products()
    {
        this.name = "";
        this.price = 0;
        this.quantity = 0;
    }
    
    public Products(String name, double price , int quantity)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
   
    @Override 
    public String toString()
    {
        return "\nName : " + this.name + "\nPrice : " + this.price + "\nQuantity : " + this.quantity;       
    }
    
    public void getDataaFromFile(String path)
    {
        String name2 = "";
        double price2;
        int quantity2;
        
        File f = new File(path);
        try
        {
            if(!f.exists())
            {
                f.createNewFile();
            }
           sc = new Scanner(f);
           while(sc.hasNext())
           {
              name2 = sc.next();
              namelist.add(name2);
              
              price2 = sc.nextDouble();
              pricelist.add(price2);
                
              quantity2 = sc.nextInt();
              quantitylist.add(quantity2);
           }
           sc.close();
           
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
     public void writeDataToFile(String n, double p, int q)
    {

        FileWriter fw;
        PrintWriter pw;
       
        try
        {
            
           sc = new Scanner(new File("files\\Items\\itemsList.txt"));
           while(sc.hasNext())
           {
              name = sc.next();
              namelist.add(name);
              
              price = sc.nextDouble();
              pricelist.add(price);
                
              quantity = sc.nextInt();
              quantitylist.add(quantity);
           }
                    
           fw = new FileWriter("files\\Items\\itemsList2.txt");
           pw = new PrintWriter(fw);     
           
            for (int i = 0; i < lineNum("files\\Items\\itemsList2.txt") + 1; i++)
            {
               if(n.equals(namelist.get(i)))
                {
                   pw.print(n);
                   pw.print(" ");
                   pw.print(p);
                   pw.print(" ");
                   pw.print(q);
                   pw.println();
                   
                }
               
               else
               {
                   pw.print(namelist.get(i));
                   pw.print(" ");
                   pw.print(pricelist.get(i));
                   pw.print(" ");
                   pw.print(quantitylist.get(i));
                   pw.println();
               }
            
            }
            
          pw.close(); 
           
        } catch(Exception e)
        {
           // System.out.println("Error in writing");
            e.printStackTrace();
        }
       // return namelist.toString();
       
    }
    
     public int arrayListEditOnDeletion(String itemName)
     {
         int itemquantity = -1;
         for (int i = 0; i < namelist.getSize(); i++) {
             if(itemName.equals(namelist.get(i)))
             {
                 itemquantity = quantitylist.get(i);
                 itemquantity ++ ;
                 quantitylist.set(i, itemquantity);
                 System.out.println(itemquantity);
             }
         }
         return itemquantity;
     }
     
     public int arrayListEditOnAddition(String name)
     {
         int quantity = -1;
         for (int i = 0; i < namelist.getSize(); i++) {
             if(name.equals(namelist.get(i)))
             {
                 quantity = quantitylist.get(i);
                 quantity --;
                 quantitylist.set(i, quantity);
             }
         
         }
         return quantity;
     }
     
     public int lineNum(String path) 
     {
         int ln = 0;
      try
      {
        LineNumberReader lnr = new LineNumberReader(new FileReader(path));
        lnr.skip(Long.MAX_VALUE);
        ln = lnr.getLineNumber();
        lnr.close();
        
      }catch (Exception e)
      {
          System.out.println("Error in line");
      }
      return ln;
    }
     
     
     
     public String printN(int i)
     {
         return namelist.get(i);
     }
     
     public double printP(int i)
     {
         return pricelist.get(i);
     }
     
     public int printQ(int i)
     {
         return quantitylist.get(i);
     }
    
}
