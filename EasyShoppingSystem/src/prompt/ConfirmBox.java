/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prompt;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author zainm
 */
public class ConfirmBox 
{
     static boolean answer;
     public static boolean display (String title, String message)
    {
       
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setResizable(false);
        
        GridPane grid1 = new GridPane();
        grid1.setPadding(new Insets(20,20,20,20));
        grid1.setVgap(0);
        grid1.setHgap(10);
        Label label1 = new Label();
        label1.setText(message);
        GridPane.setConstraints(label1, 3, 0);
        grid1.getChildren().add(label1);
        
        GridPane grid2 = new GridPane();
        grid2.setPadding(new Insets(20,20,20,20));
        grid2.setVgap(0);
        grid2.setHgap(10);
        
        Button button1 = new Button("Yes");
        button1.setOnAction(e -> {
            answer = true;
            window.close();
        });
        
        GridPane.setConstraints(button1, 9, 0);
        
        Button button2 = new Button("No");
         button2.setOnAction(e -> {
            answer = false;
            window.close();
        });
        GridPane.setConstraints(button2, 10, 0);
        grid2.getChildren().addAll(button1,button2);
       
        BorderPane layout = new BorderPane();
        layout.setTop(grid1);
        layout.setCenter(grid2);
        
        Scene scene = new Scene(layout,300,100);
        window.setScene(scene);
        scene.getStylesheets().add("Style.css");
        window.showAndWait();
        
        
       
        
        return answer;
        
    }
}
