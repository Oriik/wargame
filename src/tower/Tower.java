/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import static tower.Constantes.cellWidth;

/**
 *
 * @author Guillaume
 */
public class Tower extends Rectangle {
    public String imgPath;

    public Tower() {
        super(cellWidth,cellWidth);
        this.imgPath = "tower.png";
        Image image = new Image(getClass().getResource(imgPath).toString());
        this.setFill(new ImagePattern(image));
    }
    
    
}
