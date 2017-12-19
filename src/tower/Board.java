/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static tower.Cell.temp;
import static tower.Constantes.boardHeight;
import static tower.Constantes.boardWidth;
import static tower.Constantes.cellWidth;

/**
 *
 * @author Guillaume
 */
public class Board extends GridPane {

    private final ArrayList<Player> players; //Liste des joueurs
    private final ArrayList<Collectible> collectibles; //Liste des objets à ramasser
    private Player current_player; //Joueur en train de joueur
    private int playerCpt; //Compteur utiliser pour changer de joueur à la fin du tour

    //Constructeur
    public Board(ArrayList<Player> _players, ArrayList<Collectible> _collectibles) {
        this.players = _players;
        this.collectibles = _collectibles;
        playerCpt = 0;

    }

    /*
    Fonction d'initialisation
    Utile car certaines fonctions lèvent des Warnings si utilisées dans le constructeur 
     */
    public void initialize() {

        ///On créé un tableau de X x Y case (Cell)
        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {

                Cell cell = new Cell(this);
                cell.initCell();
                this.add(cell, y, x);
            }
        }

        /*On gère l'évènement de Drag car le drag est fait sur le plateau de jeu 
        et pas sur UNE case, donc on doit le gérer ici*/
        this.setOnMouseDragged((MouseEvent event) -> {

            //Si aucune unité n'est sélectionnée, on ne fait rien
            if (current_unit != null) {
                int posX = ((int) Math.floor(event.getX() / (cellWidth + 1)));
                int posY = ((int) Math.floor(event.getY() / (cellWidth + 1)) * boardHeight);
                //Temp est une liste static de la classe Cell, qui va contenir les cases sur lesquelles on a drag
                if (temp.size() < current_unit.move) {
                    Cell tempCell = (Cell) this.getChildren().get(posX + posY);
                    //Si on n'a pas déjà drag sur la case et que ce n'est pas la case sur laquelle est notre unité,on l'ajoute
                    if (!temp.contains(tempCell) && !tempCell.getChildren().contains(current_unit)) {
                        if (current_unit.onRange(tempCell)) {
                            tempCell.color(Color.DARKGRAY);
                        }
                        temp.add(tempCell);
                    }
                }

            }
        });//Fin setOnMouseDragged

        //On place de manière aléatoire les collectibles 
        Random r = new Random();

        for (Collectible collectible : collectibles) {
            Cell temp = (Cell) this.getChildren().get(r.nextInt(boardHeight*boardWidth));
            temp.getChildren().add(collectible);
        }

    }

    //Lors d'un nouveau tour, on change de joueur et on réinitialise les unités
    public void newTurn() {
        if (playerCpt < players.size() - 1) {
            current_player = players.get(playerCpt);
            playerCpt++;
        } else {
            current_player = players.get(playerCpt);
            playerCpt = 0;
        }

        for (Unit unit : current_player.getUnits()) {
            unit.newTurn();
        }
        uncolorCells();
        
    }

    //Permet d'afficher une unité sur le plateau de jeu (position random pour l'instant)
    public void addUnitOnBoard(Unit unit) {
        Random r = new Random();
        Cell temp;
        do {
            temp = (Cell) this.getChildren().get(r.nextInt(boardHeight*boardWidth));
        } while (temp.getChildren().size() > 1);
        temp.getChildren().add(unit);

    }

    //Décolore toutes les cases (après le déplacement d'un joueur notamment)
    public void uncolorCells() {
        for (Node children : this.getChildren()) {
            if (children instanceof Cell) {
                Rectangle tempRec = (Rectangle) ((Cell) children).getChildren().get(0);
                tempRec.setFill(Color.TRANSPARENT);
            }
        }
    }
    //Getters et Setters
    public void setCurrent_player(Player current_player) {
        this.current_player = current_player;
    }

    public void setCurrent_unit(Unit current_unit) {
        this.current_unit = current_unit;
    }
    private Unit current_unit;

    public Player getCurrent_player() {
        return current_player;
    }

    public Unit getCurrent_unit() {
        return current_unit;
    }

}
