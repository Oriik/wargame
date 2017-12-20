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
import static tower.Constantes.rateManaGold;
import static tower.Constantes.warriorPrice;

/**
 *
 * @author Guillaume
 */
class BuyUnitManaAlert extends Alert{
     public BuyUnitManaAlert(Player player) {
        super(Alert.AlertType.CONFIRMATION);
        
        this.setTitle("INVOCATION");
        this.setHeaderText(null);
        this.setContentText("Quelle unité voulez-vous invoquer grâce à votre mana ?");
        
        ButtonType buttonWarrior = new ButtonType("Guerrier - "+warriorPrice/rateManaGold);
        ButtonType buttonBowman = new ButtonType("Archer - "+bowmanPrice/rateManaGold);
        ButtonType buttonHorseman = new ButtonType("Cavalier - "+horsemanPrice/rateManaGold);
        ButtonType buttonTypeCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);

        this.getButtonTypes().setAll(buttonWarrior, buttonBowman, buttonHorseman, buttonTypeCancel);

        Optional<ButtonType> result = this.showAndWait();
        if (result.get() == buttonWarrior) {
            player.buyWarrior("mana");
        } else if (result.get() == buttonBowman) {
            player.buyBowman("mana");
        } else if (result.get() == buttonHorseman) {
            player.buyHorseman("mana");
        }
    }
}
