/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.util.ArrayList;
import javafx.event.EventType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Guillaume
 */
public class Game extends GridPane {

    public void initGame() {
        ArrayList<Player> players = new ArrayList();
        Player player1 = new Player(Color.RED,"Oriik");
        Grunt unit1 = new Grunt();
        player1.addUnit(unit1);
        players.add(player1);
        Collectible piece = new Collectible();
        Collectible piece2 = new Collectible();
        ArrayList<Collectible> collectibles = new ArrayList();
        collectibles.add(piece);
        collectibles.add(piece2);
        Board board = new Board(players, collectibles);
        board.initialize();
        Menu menu = new Menu(board);
        menu.initMenu();

        this.add(board, 0, 0);
        this.add(menu, 1, 0);
        this.addEventHandler(EventType.ROOT, new MyEventHandler(menu));
    }

}
