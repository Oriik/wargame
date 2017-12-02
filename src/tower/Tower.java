/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventType;
import javafx.scene.Cursor;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Guillaume
 */
public class Tower extends Application {

    @Override
    public void start(Stage primaryStage) {

        Board board = new Board();

        Unit unit1 = new Unit();
        Unit unit2 = new Unit();
        Unit unit3 = new Unit();
        Unit unit4 = new Unit();
        ArrayList<Unit> units = new ArrayList();
        units.add(unit1);
        units.add(unit2);
        units.add(unit3);
        units.add(unit4);
        Collectible piece = new Collectible();
        Collectible piece2 = new Collectible();
        ArrayList<Collectible> collectibles = new ArrayList();
        collectibles.add(piece);
        collectibles.add(piece2);
        board.initBoard(units, collectibles);

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
