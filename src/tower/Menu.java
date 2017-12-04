/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import static tower.Board.cpt;

/**
 *
 * @author Guillaume
 */
public class Menu extends GridPane {

    private final Board board;
    public Menu(Board _board) {
        board = _board;
    }

     Label labelScore = new Label("Score");
    Label score = new Label(Integer.toString(cpt));
    Button endOfTurn = new Button("Fin du tour");
    
    public void initMenu(){
        this.add(labelScore,0,0);
        this.add(score,1,0);
        this.add(endOfTurn,0,2);
        endOfTurn.setOnAction((ActionEvent e) -> {
           board.endTurn();
        });
    }
    
    public void refreshScore() {
        this.getChildren().remove(score);
        score = new Label(Integer.toString(cpt));
        this.add(score, 1, 0);
    }

}
