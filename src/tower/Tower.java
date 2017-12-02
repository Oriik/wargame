/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Guillaume
 */
public class Tower extends Application {

    //static Player current_player = new Player();
    @Override
    public void start(Stage primaryStage) {

        Board board = new Board();

        Unit player1 = new Unit();
        Unit player2 = new Unit();
        Unit player3 = new Unit();
        Unit player4 = new Unit();
        ArrayList<Unit> players = new ArrayList();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        Collectible piece = new Collectible();
        Collectible piece2 = new Collectible();
        ArrayList<Collectible> collectibles = new ArrayList();
        collectibles.add(piece);
        collectibles.add(piece2);
        board.initBoard(players, collectibles);

        Scene scene = new Scene(board);
        scene.addEventHandler(EventType.ROOT, new MyEventHandler(board));
        primaryStage.setTitle("Tower");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
