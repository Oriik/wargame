/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Guillaume
 */
public class Board extends GridPane {

    public static int cpt = 0;
    Label labelScore = new Label("Score");
    Label score = new Label(Integer.toString(cpt));

    public void initBoard(ArrayList<Unit> players, ArrayList<Collectible> collectibles) {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {

                Cell cell = new Cell();
                cell.initCell(this);
                this.add(cell, y, x);
            }
        }

        this.add(labelScore, 0, 10);
        this.add(score, 1, 10);

        Cell temp = (Cell) this.getChildren().get(0);
        temp.getChildren().add(players.get(0));

        temp = (Cell) this.getChildren().get(1);
        temp.getChildren().add(players.get(1));

        temp = (Cell) this.getChildren().get(2);
        temp.getChildren().add(players.get(2));

        temp = (Cell) this.getChildren().get(3);
        temp.getChildren().add(players.get(3));

        temp = (Cell) this.getChildren().get(5);
        temp.getChildren().add(collectibles.get(0));

        temp = (Cell) this.getChildren().get(10);
        temp.getChildren().add(collectibles.get(1));
    }

    public void refreshScore() {
        this.getChildren().remove(score);
        score = new Label(Integer.toString(cpt));
        this.add(score, 1, 10);
    }

}
