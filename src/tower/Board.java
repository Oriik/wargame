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

    public ArrayList<Player> players; //Liste des joueurs
    private Player current_player; //Joueur en train de joueur
    private int playerCpt;//Compteur utiliser pour changer de joueur à la fin du tour
    private Unit current_unit;
    public ArrayList<Resource> resources;

    //Constructeur
    public Board(ArrayList<Player> _players) {
        this.players = _players;
        this.resources = new ArrayList();
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
        /*On ajoute la tour et les cristaux ici 
        * car on ne les sauvegarde pas étant donné qu'ils ne contiennent pas d'informations
        */
        
        ((Cell)this.getChildren().get(boardWidth*(boardHeight/2) + boardWidth/2)).getChildren().add(new Tower());
        ((Cell)this.getChildren().get(boardWidth*(boardHeight/4) + boardWidth/4)).getChildren().add(new Crystal());
        ((Cell)this.getChildren().get(boardWidth*(boardHeight/4) + (3*boardWidth/4))).getChildren().add(new Crystal());
        ((Cell)this.getChildren().get(boardWidth*(3*boardHeight/4) + boardWidth/4)).getChildren().add(new Crystal());
        ((Cell)this.getChildren().get(boardWidth*(3*boardHeight/4) + (3*boardWidth/4))).getChildren().add(new Crystal());
        

        /*On gère l'évènement de Drag car le drag est fait sur le plateau de jeu 
        et pas sur UNE case, donc on doit le gérer ici*/
        this.setOnMouseDragged((MouseEvent event) -> {

            //Si aucune unité n'est sélectionnée, on ne fait rien
            if (current_unit != null) {
                int posX = ((int) Math.floor(event.getX() / (cellWidth + 1)));
                int posY = ((int) Math.floor(event.getY() / (cellWidth + 1)) * boardHeight);
                //On gère ici le cas mode déplacement
                if (!current_player.modeAttack) {
                    //Temp est une liste static de la classe Cell, qui va contenir les cases sur lesquelles on a drag
                    if (temp.size() < current_unit.move) {
                        Cell tempCell = (Cell) this.getChildren().get(posX + posY);
                        //Si on n'a pas déjà drag sur la case et que ce n'est pas la case sur laquelle est notre unité,on l'ajoute
                        if (!temp.contains(tempCell) && !tempCell.getChildren().contains(current_unit)) {
                            if (current_unit.onMoveRange(tempCell)) {
                                tempCell.color(Color.DARKGRAY);
                                temp.add(tempCell);
                            }
                        }
                    }
                    //On gère ici le cas mode attaque
                } else if (temp.size() < current_unit.range) {
                    Cell tempCell = (Cell) this.getChildren().get(posX + posY);
                    /*Si on n'a pas déjà drag sur la case, que ce n'est pas la case sur laquelle est notre unité,
                     et qu'elle contient une unité, on l'ajoute*/
                    if (!temp.contains(tempCell) && !tempCell.getChildren().contains(current_unit)) {
                        if (tempCell.getChildren().size() > 1 && current_unit.onAttackRange(tempCell)
                                && (tempCell.getChildren().get(1) instanceof Unit)) {
                            tempCell.color(Color.DARKRED);
                            temp.add(tempCell);
                        }
                    }
                }
            }
        });//Fin setOnMouseDragged
    }

    public void initResources() {
        Random random = new Random();
        for (int i = 0; i < boardWidth / 2; i++) {
            Cell temp;
            int pos;
            do {
                pos=random.nextInt(boardWidth * boardHeight);
                temp = (Cell) this.getChildren().get(pos);
            } while (temp.getChildren().size() > 1);
            Mine mine = new Mine();
            mine.position=pos;
            addResourceOnBoard(mine, pos);
            resources.add(mine);
        }
    }

    /*Lors d'un nouveau tour, on change de joueur, on affiche les unités créées au tour précédent,
    et on réinitialise les unités */
    public void newTurn() {
        if (playerCpt < players.size() - 1) {
            current_player = players.get(playerCpt);
            playerCpt++;
        } else {
            current_player = players.get(playerCpt);
            playerCpt = 0;
        }
        for(Unit unit :current_player.waiting){
            current_player.getUnits().add(unit);
            addUnitOnBoard(unit);
        }
        current_player.alreadyDropMana=false;
        current_player.waiting.clear();
        for (Unit unit : current_player.getUnits()) {
            unit.newTurn();
        } 
        uncolorCells();

    }

    //Permet d'afficher une unité sur le plateau de jeu (position random pour l'instant)
    public void addUnitOnBoard(Unit unit) {
        int i;
        Cell temp;
        if (unit.faction.compareTo("human") == 0) {
            i = 1;
            do {
                temp = (Cell) this.getChildren().get(i++);
            } while (temp.getChildren().size() > 1);
        } else {
            i = boardHeight * boardWidth - 2;
            do {
                temp = (Cell) this.getChildren().get(i--);
            } while (temp.getChildren().size() > 1);
        }
        temp.getChildren().add(unit);
        int posX = ((int) Math.floor(temp.getLayoutX() / (cellWidth + 1)));
        int posY = ((int) Math.floor(temp.getLayoutY() / (cellWidth + 1)) * boardHeight);
        unit.position = posX + posY;
    }

    public void addUnitOnBoard(Unit unit, int position) {

        ((Cell) this.getChildren().get(position)).getChildren().add(unit);
    }
    public void addResourceOnBoard(Resource resource, int position){
        ((Cell) this.getChildren().get(position)).getChildren().add(resource);
    }

    public void addCastleOnBoard(Castle castle) {
        if (castle.faction.compareTo("human") == 0) {
            addUnitOnBoard(castle, 0);
            castle.position = 0;
        } else {
            addUnitOnBoard(castle, boardHeight * boardWidth - 1);
            castle.position = boardHeight * boardWidth - 1;
        }
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

    public Player getCurrent_player() {
        return current_player;
    }

    public Unit getCurrent_unit() {
        return current_unit;
    }

}
