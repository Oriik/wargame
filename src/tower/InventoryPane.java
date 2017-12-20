/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.io.Serializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author Guillaume
 */
public class InventoryPane extends Pane {

    public HBox hbox;
    public InventoryPane(){
        hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        this.getChildren().add(hbox);
    }

    public void refresh( int gold, int mana) {
        hbox.getChildren().clear();
        hbox.getChildren().add(new Label("  Or : "));
        hbox.getChildren().add(new Label(Integer.toString(gold)));
        hbox.getChildren().add(new Label(" |  Mana : "));
        hbox.getChildren().add(new Label(Integer.toString(mana)));
        
    }

}
