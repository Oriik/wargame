/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Guillaume
 */
public class Menu extends GridPane {

    public Label labelScore;
    public Label score;
    public Label labelCurrentPlayer;
    public Label currentPlayer;
    public Button endOfTurn;
    public Image image;
    public ImageView imv = new ImageView();

    /*Menu sur la droite de l'écran de jeu, affiche le nom du joueur en cours,
    son score, l'image de l'unité sélectionnée et le bouton fin de tour*/
    public Menu() {
        this.score = new Label(Integer.toString(0));
        this.endOfTurn = new Button("Fin du tour");
        this.labelScore = new Label("Score");
        this.labelCurrentPlayer = new Label("En train de jouer : ");
        this.currentPlayer = new Label("");
    }

     /*
    Fonction d'initialisation
    Utile car certaines fonctions lèvent des Warnings si utilisées dans le constructeur 
     */
    public void initMenu() {
        this.add(labelScore, 0, 1);
        this.add(score, 1, 1);
        this.add(endOfTurn, 0, 0);
        this.add(labelCurrentPlayer, 0, 2);
        this.add(currentPlayer, 1, 2);
        imv.setFitWidth(150);
        imv.setFitHeight(200);
        this.add(imv, 0, 3);

        endOfTurn.setOnAction((ActionEvent e) -> {
            ((Game) this.getParent()).newTurn();
        });
    }

    public void refreshScore(int _score) {
        this.getChildren().remove(score);
        score = new Label(Integer.toString(_score));
        this.add(score, 1, 1);
    }

}
