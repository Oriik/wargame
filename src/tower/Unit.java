/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Guillaume
 */
public class Unit extends Circle {

    public int range=4;
    public int move = range;
    public Unit() {
        super(5, Color.RED);
    }
    public void endOfTurn(){
        move = range;
        Cell temp = (Cell)this.getParent();
        temp.uncolorCells();
    }
    
    
}
