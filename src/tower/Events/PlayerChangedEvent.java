/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower.Events;

import javafx.event.Event;
import static tower.Constantes.myEventType;

/**
 *
 * @author Guillaume
 */
public class PlayerChangedEvent extends Event{
    
    public PlayerChangedEvent() {
        super(myEventType);
    }
    
}
