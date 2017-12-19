/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Guillaume
 */
public class Tower extends Application {

    private Game game;
    private Home home;
    private Stage stage;
    Rectangle2D screenSize;

    @Override
    public void start(Stage primaryStage) {

        screenSize = Screen.getPrimary().getVisualBounds();
        stage = primaryStage;
        game = new Game();
        game.initGame();
        home = new Home();
        home.btnStart.setOnAction((ActionEvent event) -> {
            startGame();
        });
        Scene scene = new Scene(home, screenSize.getWidth(), screenSize.getHeight());
        stage.setTitle("Tower");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }

    //Fonction qui lance le jeu une fois que les noms et couleurs des joueurs ont été sélectionés
    private void startGame() {
        Scene scene2 = new Scene(game, screenSize.getWidth(), screenSize.getHeight());
        stage.setScene(scene2);
        game.start(home.player1Name.getText(),
                home.player2Name.getText(),
                (String) home.couleurJoueur1.getSelectionModel().getSelectedItem(),
                (String) home.couleurJoueur2.getSelectionModel().getSelectedItem());

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
