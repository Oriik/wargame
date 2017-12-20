/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 *
 * @author Guillaume
 */
public abstract class Building extends Unit {

    String destroyImgPath;

    public Building(String _faction, String _imgPath, String _destroyImgPath) {
        super(0, 0, 500, 0, 0, _faction, _imgPath);
        faction = _faction;
        destroyImgPath = _faction + "/" + _destroyImgPath;
    }

    @Override
    public void isAttacked(int damage) {
        health -= damage;
        if (health <= 0) {
            Image image = new Image(getClass().getResource(destroyImgPath).toString());
            this.setFill(new ImagePattern(image));

        }
    }

}
