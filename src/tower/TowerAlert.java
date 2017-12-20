/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Guillaume
 */
public class TowerAlert extends Alert {

    public TowerAlert(Player player) {
        super(Alert.AlertType.CONFIRMATION);
        this.setTitle("TOUR MAGIQUE");
        this.setHeaderText(null);
        this.setContentText("Bienvenue dans la tour magique, que souhaitez-vous faire ?");

        ButtonType buttonMana = new ButtonType("Déposer 100 mana");
        ButtonType buttonSummon = new ButtonType("Invoquer des unités");
        ButtonType buttonTypeCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);

        this.getButtonTypes().setAll(buttonMana, buttonSummon, buttonTypeCancel);

        Optional<ButtonType> result = this.showAndWait();
        if (result.get() == buttonMana) {
            player.dropMana();
        } else if (result.get() == buttonSummon) {
            BuyUnitManaAlert alert = new BuyUnitManaAlert(player);
        }
    }

}
