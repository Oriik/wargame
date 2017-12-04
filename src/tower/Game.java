/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.util.ArrayList;
import javafx.event.EventType;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Guillaume
 */
public class Game extends GridPane {

    public void initGame() {
        Unit unit1 = new Unit();
        Unit unit2 = new Unit();
        Unit unit3 = new Unit();
        Unit unit4 = new Unit();
        ArrayList<Unit> units = new ArrayList();
        units.add(unit1);
        units.add(unit2);
        units.add(unit3);
        units.add(unit4);
        Collectible piece = new Collectible();
        Collectible piece2 = new Collectible();
        ArrayList<Collectible> collectibles = new ArrayList();
        collectibles.add(piece);
        collectibles.add(piece2);
        Board board = new Board(units, collectibles);
        board.initialize();
        Menu menu = new Menu(board);
        menu.initMenu();

        this.add(board, 0, 0);
        this.add(menu, 1, 0);
        this.addEventHandler(EventType.ROOT, new MyEventHandler(menu));
    }

}
