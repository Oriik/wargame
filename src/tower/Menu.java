/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Guillaume
 */
public class Menu extends GridPane {

    public Label labelCurrentPlayer;
    public Label currentPlayer;
    public Button endOfTurn;
    public Player current_player;
    public Button addUnit;
    public Button sauvegarde;

    public void setCurrent_player(Player current_player) {
        this.current_player = current_player;
    }

    /*Menu sur la droite de l'écran de jeu, affiche le nom du joueur en cours,
    son score, l'image de l'unité sélectionnée et le bouton fin de tour*/
    public Menu() {
        this.endOfTurn = new Button("Fin du tour");
        this.sauvegarde= new Button("Save");
        this.labelCurrentPlayer = new Label("En train de jouer : ");
        this.currentPlayer = new Label("");
        this.addUnit = new Button("Achat Unité");

        //Bouton fin de tour
        endOfTurn.setOnAction((ActionEvent e) -> {
            ((Game) this.getParent()).newTurn();
        });
        
        //Bouton achat d'une nouvelle unité
        addUnit.setOnAction((ActionEvent e) -> {
            current_player.addWarrior();   
        });
        
        sauvegarde.setOnAction((ActionEvent e) ->{
            try {
                ((Game) this.getParent()).sauvegardeBDD("test.txt");
            } catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /*
    Fonction d'initialisation
    Utile car certaines fonctions lèvent des Warnings si utilisées dans le constructeur 
     */
    public void initMenu() {
        this.add(endOfTurn, 0, 0);
        this.add(labelCurrentPlayer, 0, 2);
        this.add(currentPlayer, 1, 2);
        this.add(addUnit, 0, 4);
        this.add(sauvegarde,1,0);

    }



    public void newTurn(Player current_player) {
        this.getChildren().clear();
        this.initMenu();
        this.current_player = current_player;
        currentPlayer.setText(current_player.getName());
        //On affiche les unités possédées par le joueur courant
        int x = 0, y = 5;
        for (Unit unit : current_player.getUnits()) {
            Button btn = new Button(unit.getClass().getSimpleName());
            btn.setOnAction((ActionEvent e) -> {
                ((Game)this.getParent()).focusCameraOnUnit(unit);
            });
            btn.setStyle("-fx-background-color: Transparent;");
            this.add(btn, x, y++);
        }

    }

}
