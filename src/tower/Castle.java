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

/**
 *
 * @author Guillaume
 */
public class Castle extends Building {

    public Castle(String faction) {
        super(faction, "castle.png", "castleDestroy.png");
    }

    @Override
    public void isAttacked(int damage) {
        super.isAttacked(damage);
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
