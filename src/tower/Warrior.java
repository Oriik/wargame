/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import static tower.Constantes.warriorPrice;

/**
 *
 * @author Guillaume
 */
public class Warrior extends Unit {

    public Warrior(String faction) {
        super(1, 4, 180, 30, warriorPrice, faction, "warrior.png");
    }

}
