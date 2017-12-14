/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author Guillaume
 */
public class Player {
    
    public Color playerColor;
    public String name;
    public int score=0;
    public ArrayList<Unit> units ;

    

    public Player(Color _playerColor, String _name) {
        this.playerColor = _playerColor;
        this.name = _name;
        units = new ArrayList();
    }
    

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    
    
}
