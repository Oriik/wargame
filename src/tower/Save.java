/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Guillaume
 */
public class Save implements Serializable{
    
    ArrayList<Player> players;
    ArrayList<Resource> resources;
    Player current_player;

    public Save(ArrayList<Player> players, ArrayList<Resource> resources) {
        this.players = players;
        this.resources = resources;
    }
    
    
}
