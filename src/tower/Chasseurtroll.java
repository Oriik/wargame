/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * @author Jordi
 */
public class Chasseurtroll extends Unit {
    
        
    public Chasseurtroll(Color color) {
        super(1, 4, color);
        this.img = new Image(getClass().getResource("troll.jpg").toString());
    }
    
}
