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
public class EndGameAlert extends Alert {

    public EndGameAlert() {
        super(Alert.AlertType.WARNING);

        this.setTitle("FIN DU JEU !");
        this.setHeaderText(null);
        this.setContentText("Bravo, force et honneur à toi !");

        Optional<ButtonType> result = this.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }
}
