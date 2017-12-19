/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 *
 * @author Guillaume
 */
public abstract class Building extends Unit {

    String destroyImgPath;

    public Building(String _faction, String _imgPath, String _destroyImgPath) {
        super(0,0,500,0,_faction,_imgPath);
        faction = _faction;
        destroyImgPath = _faction+"/"+_destroyImgPath;
    }

    @Override
    public void isAttacked(int damage) {
        health -= damage;
        if (health <= 0) {
        Image image = new Image(getClass().getResource(destroyImgPath).toString());
        this.setFill(new ImagePattern(image));
          Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("FIN DU JEU !");
                            alert.setHeaderText(null);
                            alert.setContentText("FIN DU JEU!");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                Platform.exit();
                            }
        }
    }

}
