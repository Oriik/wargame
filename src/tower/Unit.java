/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import static tower.Constantes.cellWidth;
import tower.Events.CellBusyEvent;
import tower.Events.OutOfRangeEvent;
import tower.Events.PickCollectibleEvent;

/**
 *
 * @author Guillaume
 */
abstract public class Unit extends Rectangle {

    public int moveMax;
    public int range;
    public int move;
    public int damage;
    public int health;
    public boolean alreadyAttack;
    public String faction;

    //Constructeur
    public Unit(int _range, int _moveMax, int _health, int _damage, String _faction, String imgPath) {
        super(cellWidth, cellWidth);
        Image image = new Image(getClass().getResource(_faction + "/" + imgPath).toString());
        this.setFill(new ImagePattern(image));
        this.moveMax = _moveMax;
        this.range = _range;
        move = moveMax;
        this.health = _health;
        this.damage = _damage;
        alreadyAttack = false;
        this.faction = _faction;
    }

    //Réinitialise le nombre de mouvement possible
    public void newTurn() {
        move = moveMax;
        alreadyAttack=false;
    }

    //Déplace l'unité surla case destination
    public void move(Cell dest) {

        //On vérifie si la case destination est occupée
        if (dest.getChildren().size() > 1 && dest.getChildren().get(1) instanceof Unit) {
            /* Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("ENNEMI EN VU !");
            alert.setHeaderText(null);
            alert.setContentText("Un ennemi nous barre la route, voulez-vous les attaquer ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                this.attack((Unit) dest.getChildren().get(1));
            } else {
                return;
            }*/
            CellBusyEvent temp = new CellBusyEvent();
            this.fireEvent(temp);
            return;
        }
        Cell tempCell = (Cell) this.getParent();
        //On vérifie si la case destination est à portée
        if (!onMoveRange(dest)) {
            OutOfRangeEvent tempEvent = new OutOfRangeEvent();
            this.fireEvent(tempEvent);
            return;
        }
        //On vérifie si un collectible est sur la case destination et on le ramasse
        if (dest.getChildren().size() > 1 && dest.get(1) instanceof Collectible) {
            dest.getChildren().remove(1);
            PickCollectibleEvent temp = new PickCollectibleEvent();
            this.fireEvent(temp);
            temp.consume();
        }
        //On déplace l'unité et on réduit le compteur de mouvement de la distance
        dest.getChildren().add(1, this);
        this.move -= (int) distance(tempCell);
    }

    //Vérifie si la case tempCell est à portée de déplacement de l'unité
    public boolean onMoveRange(Cell tempCell) {
        double a = distance(tempCell);
        return (a <= this.move);
    }

    //Vérifie si la case tempCell est à portée d'attaque de l'unité
    public boolean onAttackRange(Cell tempCell) {
        double a = distance(tempCell);
        return (a <= this.range);
    }

    //Calcule la distance entre l'unité et la case tempCell
    public double distance(Cell tempCell) {
        double a = Math.abs((tempCell.getLayoutX() - this.getParent().getLayoutX()) / (Constantes.cellWidth + 1));
        double b = Math.abs((tempCell.getLayoutY() - this.getParent().getLayoutY()) / (Constantes.cellWidth + 1));
        return a + b;
    }

    //Colore les cases a portée de l'unité
    public void colorCellOnMoveRange() {
        Board father = (Board) this.getParent().getParent();
        for (Node children : father.getChildren()) {
            if (children instanceof Cell) {
                if (onMoveRange((Cell) children)) {
                    Rectangle tempRec = (Rectangle) ((Cell) children).get(0);
                    tempRec.setFill(Color.LIGHTGRAY);
                }
            }
        }
    }

    public void colorCellOnAttackRange() {
        Board father = (Board) this.getParent().getParent();
        for (Node children : father.getChildren()) {
            if (children instanceof Cell) {
                if (onAttackRange((Cell) children)) {
                    Rectangle tempRec = (Rectangle) ((Cell) children).get(0);
                    tempRec.setFill(Color.FIREBRICK);
                }
            }
        }
    }

    public void attack(Unit target) {
        if (onAttackRange((Cell) target.getParent())) {
            if (target.faction.compareTo(faction) == 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("STOP!");
                alert.setHeaderText(null);
                alert.setContentText("Cette unité est notre allié !");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    return;
                }
            }
            target.isAttacked(this.damage);
            if (target.isAlive() && target.onAttackRange((Cell) this.getParent())) {
                this.isAttacked(target.damage);
            }
            alreadyAttack = true;
        }
    }

    private void isAttacked(int damage) {
        health -= damage;
        if (!isAlive()) {
            ((Cell) this.getParent()).getChildren().remove(this);
        }
    }

    private boolean isAlive() {
        return (health > 0);
    }

}
