/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import static tower.Constantes.bowmanPrice;

/**
 *
 * @author Jordi
 */
public class Bowman extends Unit {

    public Bowman(String faction) {
        super(5, 5, 80, 40, bowmanPrice, faction, "bowman.png");
    }

}
