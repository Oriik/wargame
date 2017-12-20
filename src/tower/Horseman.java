/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import static tower.Constantes.horsemanPrice;

/**
 *
 * @author Jordi
 */
public class Horseman extends Unit {

    public Horseman(String faction) {
        super(1, 10, 150, 25, horsemanPrice, faction, "horseman.png");
    }

}
