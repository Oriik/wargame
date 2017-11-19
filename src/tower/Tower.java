/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;
    

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
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
        Collectible piece = new Collectible();
        board.initBoard(player,piece);
        
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
