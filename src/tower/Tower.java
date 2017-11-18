/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.awt.Event;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Guillaume
 */
public class Tower extends Application {
    
    @Override
    public void start(Stage primaryStage) {
    
        Board board = new Board();
               
        Player player = new Player();
        player.setRadius(5);
        
        board.initBoard(player);
                  
        //On place le player à sa position initiale
        StackPane temp = (StackPane)board.getChildren().get(0);
        temp.getChildren().add(player);

        Scene scene = new Scene(board, 300, 300);
        
        primaryStage.setTitle("Tower");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
 

  
   
}
