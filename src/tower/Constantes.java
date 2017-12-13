/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import javafx.event.EventType;
import tower.Events.PickCollectibleEvent;

/**
 *
 * @author Guillaume
 */
public class Constantes {
    
    public static int cellWidth = 20;
    
    public static EventType<PickCollectibleEvent> myEventType = new EventType<>("NOIDEAHOWITSWORKING");
}
