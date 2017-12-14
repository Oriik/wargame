/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Guillaume
 */
public class Cell extends StackPane {

    public static ArrayList<Cell> temp = new ArrayList();
    public static Unit current_unit;

    public void initCell(Board board) {

        Rectangle rec = new Rectangle();
        rec.setFill(Color.TRANSPARENT);
        rec.setStroke(Color.BLACK);
        rec.setHeight(Constantes.cellWidth);
        rec.setWidth(Constantes.cellWidth);
        this.getChildren().add(rec);

        this.setOnMouseReleased((MouseEvent event) -> {

            if (current_unit != null) {
                Iterator<Cell> it = temp.iterator();
                while (it.hasNext()) {
                    current_unit.move(it.next());
                }

            }
            temp.clear();
            uncolorCells();

        });

        this.setOnMousePressed((MouseEvent event) -> {

            if (event.getButton() == MouseButton.SECONDARY) {
                current_unit = null;
                uncolorCells();
                return;
            }
            if (this.getChildren().size() > 1 && this.getChildren().get(1) instanceof Unit) {
                current_unit = (Unit) this.getChildren().get(1);
                current_unit.colorCellOnRange();
            }       
        });
    }

    public void uncolorCells() {
        Board father = (Board) this.getParent();
        for (Node children : father.getChildren()) {
            if (children instanceof Cell) {
                Rectangle tempRec = (Rectangle) ((Cell) children).getChildren().get(0);
                tempRec.setFill(Color.TRANSPARENT);
            }
        }
    }

    public void color(Color color) {
        ((Rectangle) this.getChildren().get(0)).setFill(color);
    }

    public Node get(int i) {
        return this.getChildren().get(i);
    }
}
