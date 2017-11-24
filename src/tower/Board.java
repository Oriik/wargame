/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.util.ArrayList;
import javafx.scene.layout.GridPane;


/**
 *
 * @author Guillaume
 */
public class Board extends GridPane{
    int cpt=0;
    public void initBoard(ArrayList<Player> players, Collectible collectible){
        for(int x=0;x<10;x++){
            for(int y=0;y<10;y++){
                              
                 Cell cell = new Cell();
                 cell.initCell(this,collectible);
                 this.add(cell, y, x);
            }
        }
        
        Cell temp = (Cell)this.getChildren().get(0);
        temp.getChildren().add(players.get(0));
        
        temp = (Cell)this.getChildren().get(1);
        temp.getChildren().add(players.get(1));
        
        temp = (Cell)this.getChildren().get(2);
        temp.getChildren().add(players.get(2));
        
        temp = (Cell)this.getChildren().get(3);
        temp.getChildren().add(players.get(3));

        temp =(Cell)this.getChildren().get(5);
        temp.getChildren().add(collectible);
    }
     
 

  
}


