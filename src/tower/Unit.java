/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import tower.Events.CellBusyEvent;
import tower.Events.OutOfRangeEvent;
import tower.Events.PickCollectibleEvent;
import static tower.Constantes.myEventType;

/**
 *
 * @author Guillaume
 */
public class Unit extends Circle {

    
    public int range = 4;
    public int move = range;

    public Unit() {
        super(5, Color.RED);
    }

    public void endOfTurn() {
        move = range;
        Cell temp = (Cell) this.getParent();
        temp.uncolorCells();
    }

    
    void move(Cell dest) {

        if (dest.getChildren().size() > 1 && dest.getChildren().get(1) instanceof Unit) {
            CellBusyEvent temp = new CellBusyEvent(myEventType);
            this.fireEvent(temp);
            return;
        }
        Cell tempCell = (Cell) this.getParent();
        if (!onRange(dest)) {
            OutOfRangeEvent tempEvent = new OutOfRangeEvent(myEventType);
            this.fireEvent(tempEvent);
            return;
        }

        if (dest.getChildren().size() > 1 && dest.get(1) instanceof Collectible) {
            dest.getChildren().remove(1);
            PickCollectibleEvent temp = new PickCollectibleEvent(myEventType);
            this.fireEvent(temp);
            temp.consume();
        }
        dest.getChildren().add(1, this);
        this.move -= (int) distance(tempCell);
    }

    private boolean onRange(Cell tempCell) {
        double a = distance(tempCell);
        return (a <= this.move);
    }

    private double distance(Cell tempCell) {
        double a = Math.abs((tempCell.getLayoutX() - this.getParent().getLayoutX()) / (Constantes.cellWidth + 1));
        double b = Math.abs((tempCell.getLayoutY() - this.getParent().getLayoutY()) / (Constantes.cellWidth + 1));
        return a + b;
    }

    public void colorCellOnRange() {
        Board father = (Board) this.getParent().getParent();
        for (Node children : father.getChildren()) {
            if (children instanceof Cell) {
                if (onRange((Cell) children)) {
                    Rectangle tempRec = (Rectangle) ((Cell) children).get(0);
                    tempRec.setFill(Color.GREEN);
                }
            }
        }
    }

}
