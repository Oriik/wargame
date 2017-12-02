/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower.Events;

import javafx.event.Event;
import javafx.event.EventType;

/**
 *
 * @author Guillaume
 */
public class PickCollectibleEvent extends Event {
    
    public PickCollectibleEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }


    
}
