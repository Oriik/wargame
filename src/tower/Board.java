/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Guillaume
 */
public class Board extends GridPane{
    int cpt=0;
    public void initBoard(Player player, Collectible collectible){
        for(int x=0;x<10;x++){
            for(int y=0;y<10;y++){
                              
                 Cell cell = new Cell();
                 cell.initCell(this,player,collectible);
                 this.add(cell, y, x);
            }
        }
        
        Cell temp = (Cell)this.getChildren().get(0);
        temp.getChildren().add(player);
        
        temp =(Cell)this.getChildren().get(5);
        temp.getChildren().add(collectible);
    }
     
 

  
}


