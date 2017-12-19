/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import tower.Events.OutOfRangeEvent;
import tower.Events.PickCollectibleEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import tower.Events.CellBusyEvent;

/**
 *
 * @author Guillaume
 */
public class MyEventHandler implements EventHandler {

    private final Menu menu;
    private final Board board;

    //Classe qui va gérer tous les évènements
    public MyEventHandler(Menu _menu, Board _board) {
        menu = _menu;
        board=_board;
    }

    @Override
    public void handle(Event event) {
        if (event instanceof PickCollectibleEvent) {
            board.getCurrent_player().increaseScore(1);
            menu.refreshScore(board.getCurrent_player().getScore());
            event.consume();
        }
        if (event instanceof OutOfRangeEvent) {
            System.out.println("Case hors de votre portée !");
            event.consume();
        }
        if(event instanceof CellBusyEvent){
            System.out.println("Case déjà occupée !");
            event.consume();
        }
    }

}
