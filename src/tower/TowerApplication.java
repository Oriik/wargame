/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import static tower.Constantes.savePath;

/**
 *
 * @author Guillaume
 */
public class TowerApplication extends Application {

    private Game game;
    private Home home;
    private Stage stage;
    private Rectangle2D screenSize;
    private Save save;

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
        home.loadGame.setOnAction((ActionEvent event) -> {
            try {
                save = Game.lectureBDD(savePath);
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur ! ");
                alert.setHeaderText("Impossible de charger la partie");
                alert.setContentText("Merci de commencer une nouvelle partie");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    return;
                }
            }
            loadGame();

        });
        Scene scene = new Scene(home, screenSize.getWidth(), screenSize.getHeight());
        stage.setTitle("Tower");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }

    //Fonction qui lance le jeu une fois que les noms et couleurs des joueurs ont été sélectionés
    private void startGame() {
        Scene sceneGame = new Scene(game, screenSize.getWidth(), screenSize.getHeight());
        sceneGame.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.A) {
                game.board.getCurrent_player().modeAttack = !game.board.getCurrent_player().modeAttack;
            }
        });
        stage.setScene(sceneGame);
        game.start(home.player1Name.getText(),
                home.player2Name.getText());

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void loadGame() {
        game.loadGame(save);
        Scene sceneGame = new Scene(game, screenSize.getWidth(), screenSize.getHeight());
        sceneGame.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.A) {
                game.board.getCurrent_player().modeAttack = !game.board.getCurrent_player().modeAttack;
            }
        });
        stage.setScene(sceneGame);
    }

}
