/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Guillaume
 */
public class Tower extends Application {

    @Override
    public void start(Stage primaryStage) {

        Game game = new Game(); 
        game.initGame();
        Scene scene = new Scene(game);
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
