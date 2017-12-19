/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import javafx.scene.Node;
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
    public Image img;

    //Constructeur
    public Unit(int _range, int _moveMax, String imgPath) {
        super(cellWidth,cellWidth);  
        Image image = new Image(getClass().getResource(imgPath).toString());
        this.setFill(new ImagePattern(image));
        this.moveMax = _moveMax;
        this.range = _range;
        move=moveMax;
    }

    //Réinitialise le nombre de mouvement possible
    public void newTurn() {
        move = moveMax;       
    }

    //Déplace l'unité surla case destination
    public void move(Cell dest) {

        //On vérifie si la case destination est occupée
        if (dest.getChildren().size() > 1 && dest.getChildren().get(1) instanceof Unit) {
            CellBusyEvent temp = new CellBusyEvent();
            this.fireEvent(temp);
            return;
        }
        Cell tempCell = (Cell) this.getParent();
        //On vérifie si la case destination est à portée
        if (!onRange(dest)) {
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

    //Vérifie si la case tempCell est à portée de l'unité
    public boolean onRange(Cell tempCell) {
        double a = distance(tempCell);
        return (a <= this.move);
    }

    //Calcule la distance entre l'unité et la case tempCell
    public double distance(Cell tempCell) {
        double a = Math.abs((tempCell.getLayoutX() - this.getParent().getLayoutX()) / (Constantes.cellWidth + 1));
        double b = Math.abs((tempCell.getLayoutY() - this.getParent().getLayoutY()) / (Constantes.cellWidth + 1));
        return a + b;
    }

    //Colore les cases a portée de l'unité
    public void colorCellOnRange() {
        Board father = (Board) this.getParent().getParent();
        for (Node children : father.getChildren()) {
            if (children instanceof Cell) {
                if (onRange((Cell) children)) {
                    Rectangle tempRec = (Rectangle) ((Cell) children).get(0);
                    tempRec.setFill(Color.LIGHTGRAY);
                }
            }
        }
    }

}
