/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;
    

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Guillaume
 */
public class Tower extends Application {
    
    //static Player current_player = new Player();
    
    @Override
    public void start(Stage primaryStage) {
        
        Board board = new Board();
               
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
        ArrayList<Player> players = new ArrayList();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        Collectible piece = new Collectible();
        board.initBoard(players,piece);
        
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
