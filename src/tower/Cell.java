/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import tower.Events.PickCollectibleEvent;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tower.Events.CellBusyEvent;
import tower.Events.OutOfRangeEvent;

/**
 *
 * @author Guillaume
 */
public class Cell extends StackPane {

    public static Unit current_player;

    public static EventType<PickCollectibleEvent> myEventType = new EventType<>("NOIDEAHOWITSWORKING");

    public void initCell(Board board) {

        Rectangle rec = new Rectangle();
        rec.setFill(Color.TRANSPARENT);
        rec.setStroke(Color.BLACK);
        rec.setHeight(40);
        rec.setWidth(40);
        this.getChildren().add(rec);

        this.setOnMouseClicked((MouseEvent event) -> {

            //Si un joueur est sélectionné, on le déplace
            if (current_player != null) {
                if (this.getChildren().size() > 1 && this.getChildren().get(1) instanceof Unit) {
                    CellBusyEvent temp = new CellBusyEvent(myEventType);
                    this.fireEvent(temp);
                    return;
                }
                Cell tempCell = (Cell) current_player.getParent();
                if (!onRange(tempCell)) {
                    OutOfRangeEvent tempEvent = new OutOfRangeEvent(myEventType);
                    this.fireEvent(tempEvent);
                    return;
                }

                if (this.getChildren().size() > 1 && this.getChildren().get(1) instanceof Collectible) {
                    this.getChildren().remove(1);
                    PickCollectibleEvent temp = new PickCollectibleEvent(myEventType);
                    this.fireEvent(temp);
                    temp.consume();
                }
                moveUnit(tempCell);
                uncolorCells();

            } // Sinon on sélectionne le joueur sous la souris
            else if (this.getChildren().size() > 1 && this.getChildren().get(1) instanceof Unit) {
                current_player = (Unit) this.getChildren().get(1);
                colorCellOnRange();

            }

        });

    }

    private double distance(Cell tempCell){
        double a = (tempCell.getLayoutX() - this.getLayoutX()) / 41;
        double b = (tempCell.getLayoutY() - this.getLayoutY()) / 41;
        double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        return c;
    }
    private boolean onRange(Cell tempCell) {
        double a = distance(tempCell);
        return (a <= current_player.move);
    }

    private void colorCellOnRange() {
        Board father = (Board) this.getParent();
        for (Node children : father.getChildren()) {
            if (children instanceof Cell) {
                if (onRange((Cell) children)) {
                    Rectangle tempRec = (Rectangle) ((Cell) children).getChildren().get(0);
                    tempRec.setFill(Color.GAINSBORO);
                }
            }
        }
    }

    private void uncolorCells() {
        Board father = (Board) this.getParent();
        for (Node children : father.getChildren()) {
            if (children instanceof Cell) {
                Rectangle tempRec = (Rectangle) ((Cell) children).getChildren().get(0);
                tempRec.setFill(Color.TRANSPARENT);
            }
        }
    }

    private void moveUnit(Cell tempCell) {
        tempCell.getChildren().remove(current_player);
        this.getChildren().add(1, current_player);
        current_player.move -= (int)distance(tempCell);
        current_player = null;
    }
}
