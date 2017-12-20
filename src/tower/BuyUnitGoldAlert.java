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
import static tower.Constantes.bowmanPrice;
import static tower.Constantes.horsemanPrice;
import static tower.Constantes.warriorPrice;

/**
 *
 * @author Guillaume
 */
public class BuyUnitGoldAlert extends Alert {

    public BuyUnitGoldAlert(Player player) {
        super(AlertType.CONFIRMATION);
        
        this.setTitle("RECRUTEMENT");
        this.setHeaderText(null);
        this.setContentText("Quelle unité voulez-vous recruter ?");
        
        ButtonType buttonWarrior = new ButtonType("Guerrier - "+warriorPrice);
        ButtonType buttonBowman = new ButtonType("Archer - "+bowmanPrice);
        ButtonType buttonHorseman = new ButtonType("Cavalier - "+horsemanPrice);
        ButtonType buttonTypeCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);

        this.getButtonTypes().setAll(buttonWarrior, buttonBowman, buttonHorseman, buttonTypeCancel);

        Optional<ButtonType> result = this.showAndWait();
        if (result.get() == buttonWarrior) {
            player.buyWarrior("gold");
        } else if (result.get() == buttonBowman) {
            player.buyBowman("gold");
        } else if (result.get() == buttonHorseman) {
            player.buyHorseman("gold");
        }
    }

}
