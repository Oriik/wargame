/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import javafx.scene.image.Image;

/**
 *
 * @author Guillaume
 */
public class Warrior extends Unit {
   

    
    public Warrior(String faction) {
        super(1, 4, faction+"/warrior.png");
        this.img = new Image(getClass().getResource("Grunt.jpg").toString());
    }
    
}
