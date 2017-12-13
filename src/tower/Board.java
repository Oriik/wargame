/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import static tower.Cell.current_player;
import static tower.Cell.temp;
import static tower.Constantes.cellWidth;

/**
 *
 * @author Guillaume
 */
public class Board extends GridPane {

    public static int cpt = 0;
    private ArrayList<Unit> units;
    private ArrayList<Collectible> collectibles;

    public Board(ArrayList<Unit> _units, ArrayList<Collectible> _collectibles) {
        this.units = _units;
        this.collectibles = _collectibles;

    }

    public void initialize() {
        for (int x = 0; x < 33; x++) {
            for (int y = 0; y < 50; y++) {

                Cell cell = new Cell();
                cell.initCell(this);
                this.add(cell, y, x);
            }
        }

        this.setOnMouseDragged((MouseEvent event) -> {

            int posX = ((int) Math.floor(event.getX() / cellWidth));
            int posY = ((int) Math.floor(event.getY() / cellWidth) * 50);
            if (current_player != null) {
                Cell tempCell = (Cell)this.getChildren().get(posX+posY);
                if (!temp.contains(tempCell)) {
                    temp.add(tempCell);
                }

                        
            }
        });
        Cell temp = (Cell) this.getChildren().get(0);

        temp.getChildren()
                .add(units.get(0));

        temp = (Cell) this.getChildren().get(1);

        temp.getChildren()
                .add(units.get(1));

        temp = (Cell) this.getChildren().get(2);

        temp.getChildren()
                .add(units.get(2));

        temp = (Cell) this.getChildren().get(3);

        temp.getChildren()
                .add(units.get(3));

        temp = (Cell) this.getChildren().get(5);

        temp.getChildren()
                .add(collectibles.get(0));

        temp = (Cell) this.getChildren().get(10);

        temp.getChildren()
                .add(collectibles.get(1));

    }

    public void endTurn() {
        for (Unit unit : units) {
            unit.endOfTurn();
            Cell.current_player = null;
        }
    }
}
