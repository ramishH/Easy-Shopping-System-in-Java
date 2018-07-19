/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EasyShoppingSystem;



import prompt.ConfirmBox;
import accountHandler.UserAccount;
import accountHandler.AdminAccount;
import javafx.scene.image.Image ;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author zainm
 */
public class Proj extends Application 
{
   
    Stage window;
 
    @Override
    public void start(Stage primaryStage) throws InterruptedException 
    {
       window = primaryStage;
       window.setTitle("***Easy Shopping System***");
       window.setResizable(false);
     
       window.setOnCloseRequest(e -> {
           e.consume();
           closePer();
       });
       intro();
       Thread.sleep(2500);
       
       main();
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
   private void intro()
   {
       Group root = new Group();
       Image image = new Image(getClass().getResourceAsStream("/res/Introo.png"));
       ImageView view = new ImageView(image);
       root.getChildren().add(view);
       Scene scene = new Scene(root, image.getWidth(),image.getHeight(), Color.TRANSPARENT);
       view.setEffect(new Reflection());
      
       window.setScene(scene);
     //  window.initStyle(StageStyle.TRANSPARENT);
       window.show();
//       GridPane grid = new GridPane();
//       grid.setPadding(new Insets(10,10,10,10));
//       grid.setVgap(10);
//       grid.setHgap(10);
//       
//       Label head = new Label();
//       Image image = new Image(getClass().getResourceAsStream("/res/6.png"),800,400,true,true);
//       head.setGraphic(new ImageView(image));
//       GridPane.setConstraints(head, 0, 0);
//       grid.getChildren().add(head);
//       Scene sce = new Scene(grid,1000,400);
//      // window.initStyle(StageStyle.UNDECORATED);
//       window.setScene(sce);
//       window.show();
       
   }
    
   private void main()
   {
       GridPane layout = new GridPane();
       layout.setPadding(new Insets(10,10,10,10));
       layout.setHgap(10);
       layout.setVgap(10);
           
       Label chooseacc = new Label("Choose Account");
       Image image = new Image(getClass().getResourceAsStream("/res/account.jpg"),20,20,true,true);
       chooseacc.setGraphic(new ImageView(image));
      // chooseacc.setTextFill(Color.web("#A9DE07"));
       GridPane.setConstraints(chooseacc, 2, 0);
       
       ToggleGroup group = new ToggleGroup();
       
       RadioButton admin = new RadioButton("Admin");
       admin.setToggleGroup(group);
       GridPane.setConstraints(admin, 2, 2);
      
       RadioButton user = new RadioButton("User");
       user.setSelected(true);
       user.setToggleGroup(group);
       GridPane.setConstraints(user, 2, 3);
       
       Button next = new Button("Next");
       next.setMinWidth(40);
       GridPane.setConstraints(next, 12, 4);
       
       Button exit = new Button("Exit");
       exit.setMinWidth(40);
       GridPane.setConstraints(exit, 0, 4);
       
       layout.getChildren().addAll(/*image,*/chooseacc,admin,user,exit,next);
       
       Scene main = new Scene(layout,350,140);
       main.getStylesheets().add("Style.css");
       window.setScene(main);
      
       next.setOnAction(e -> 
       {
           
           if(admin.isSelected())
           {
           
               AdminAccount acc = new AdminAccount() {};
               try
               {
                   acc.start(window);
               }catch(Exception ez)
               {
                   System.out.println("Error in Accounts");
               }
               
              // acc.setScene();
               acc.setButtonsAction(main);
            // adminpan(main);
       
           }
           
           if(user.isSelected())
           {
           
                {
           
               UserAccount acc = new UserAccount();
               try
               {
                   acc.start(window);
               }catch(Exception ez)
               {
                   System.out.println("Error in user");
                  // ez.printStackTrace();
               }
               
              // acc.setScene();
               acc.setButtonsAction(main);
            // adminpan(main);
       
           }
               
             //userpan(main);
    
           }
        }
      );

       exit.setOnAction(e -> closePer());
 
       window.show();
        
   }
   private void closePer()
   {
       boolean result = ConfirmBox.display("Alert", "Are you sure you want to close?");
       if(result)
       {
           window.close();
       }
   }
}
