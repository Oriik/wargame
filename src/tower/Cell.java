/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import tower.Events.PickCollectibleEvent;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tower.Events.CellBusyEvent;
import tower.Events.OutOfRangeEvent;

/**
 *
 * @author Guillaume
 */
public class Cell extends StackPane {

    public static Unit current_player;

    public static EventType<PickCollectibleEvent> myEventType = new EventType<>("NOIDEAHOWITSWORKING");
    

    public void initCell(Board board) {

        Rectangle rec = new Rectangle();
        rec.setFill(Color.TRANSPARENT);
        rec.setStroke(Color.BLACK);
        rec.setHeight(40);
        rec.setWidth(40);
        this.getChildren().add(rec);

        this.setOnMouseClicked((MouseEvent event) -> {

            //Si un joueur est sélectionné, on le déplace
            if (current_player != null) {
                if (this.getChildren().size() > 1 && this.getChildren().get(1) instanceof Unit) {
                    CellBusyEvent temp = new CellBusyEvent(myEventType);
                    this.fireEvent(temp);
                    return;
                }
                Cell tem = (Cell) current_player.getParent();
                if (Math.abs(tem.getLayoutX() - this.getLayoutX()) / 41 > current_player.range
                        || Math.abs(tem.getLayoutY() - this.getLayoutY()) / 41 > current_player.range) {
                    OutOfRangeEvent temp = new OutOfRangeEvent(myEventType);
                    this.fireEvent(temp);
                    return;
                }

                tem.getChildren().remove(current_player);

                if (this.getChildren().size() > 1 && this.getChildren().get(1) instanceof Collectible) {
                    this.getChildren().remove(1);
                    PickCollectibleEvent temp = new PickCollectibleEvent(myEventType);
                    this.fireEvent(temp);
                    temp.consume();
                }
                this.getChildren().add(1, current_player);
                current_player = null;

            } // Sinon on sélectionne le joueur sous la souris
            else if (this.getChildren().size() > 1 && this.getChildren().get(1) instanceof Unit) {
                current_player = (Unit) this.getChildren().get(1);

            }

        });

    }
}
