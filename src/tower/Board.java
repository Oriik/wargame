/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import static tower.Cell.temp;
import static tower.Constantes.cellWidth;
import static tower.Cell.current_unit;

/**
 *
 * @author Guillaume
 */
public class Board extends GridPane {

    public static int cpt = 0;
    private final ArrayList<Player> players;
    private final ArrayList<Collectible> collectibles;

    public Board(ArrayList<Player> _players, ArrayList<Collectible> _collectibles) {
        this.players = _players;
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

            int posX = ((int) Math.floor(event.getX() / (cellWidth + 1)));
            int posY = ((int) Math.floor(event.getY() / (cellWidth + 1)) * 50);
            if (current_unit != null) {
                if (temp.size() < current_unit.move) {
                    Cell tempCell = (Cell) this.getChildren().get(posX + posY);
                    if (!temp.contains(tempCell) && !tempCell.getChildren().contains(current_unit)) {
                        if (current_unit.onRange(tempCell)) {
                            tempCell.color(Color.DARKGRAY);
                        }
                        temp.add(tempCell);
                    }
                }

            }
        });

        Random r = new Random();
        
        for (Collectible collectible : collectibles) {
            Cell temp = (Cell) this.getChildren().get(r.nextInt(1650));
            temp.getChildren().add(collectible);
        }

        for(Player player : players){
            for(Unit unit : player.getUnits()){
            Cell temp = (Cell) this.getChildren().get(r.nextInt(1650));
            temp.getChildren().add(unit);
            }
            
        }

    }

    public void endTurn() {

    }
}
