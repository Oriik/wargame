/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.io.Serializable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import static tower.Constantes.cellWidth;

/**
 *
 * @author Guillaume
 */
public abstract class Resource extends Rectangle implements Serializable {

    public int cptResource;
    public String imgPath;
    public int position;
    public int pickUp;

    public Resource(int cpt, int _pickUp, String _imgPath) {
        super(cellWidth, cellWidth);
        pickUp = _pickUp;
        cptResource = cpt;
        imgPath = _imgPath;
        Image image = new Image(getClass().getResource(_imgPath).toString());
        this.setFill(new ImagePattern(image));
    }

    public int collect() {
        if (cptResource >= pickUp) {
            cptResource -= pickUp;
            return pickUp;
        } else {
            int temp = cptResource;
            cptResource = 0;
            ((Cell) this.getParent()).getChildren().remove(this);
            return temp;
        }

    }

}
