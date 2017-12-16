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

    public Board board;
    public Menu menu;
    public ArrayList<Player> players;
    public ArrayList<Collectible> collectibles;

    /*
    Fonction d'initialisation
    Utile car certaines fonctions lèvent des Warnings si utilisées dans le constructeur 
     */
    public void initGame() {
        //On créé les deux joueurs, avec une valeur par défaut pour leurs noms
        players = new ArrayList();
        Player player1 = new Player("Joueur 1");
        Player player2 = new Player("Joueur 2");
        players.add(player1);
        players.add(player2);

        Collectible piece = new Collectible();
        Collectible piece2 = new Collectible();
        collectibles = new ArrayList();
        collectibles.add(piece);
        collectibles.add(piece2);
        board = new Board(players, collectibles);
        board.initialize();
        menu = new Menu();
        menu.initMenu();

        this.add(board, 0, 0);
        this.add(menu, 1, 0);
        //Tous les évènements seront géré par une instance de la classe MyEventHandler
        this.addEventHandler(EventType.ROOT, new MyEventHandler(menu, board));
    }


    public void newTurn() {
        board.newTurn();
    }

    //On lance le jeu, fonction appelé après l'écran Home
    void start(String player1Name, String player2Name, String colorP1, String colorP2) {
        //On nomme les joueurs, si ils ont saisie un nom
        if (!"".equals(player1Name)) {
            players.get(0).setName(player1Name);
        }
        if (!"".equals(player2Name)) {
            players.get(1).setName(player2Name);
        }
        //On attribue les bonnes couleurs
        players.get(0).setColorFromString(colorP1);
        players.get(1).setColorFromString(colorP2);
        //On créé une unité pour chaque joueur pour commencer la partie
        players.get(0).addGrunt();
        players.get(1).addGrunt();
        board.addUnitOnBoard(players.get(0).getUnits().get(0));
        board.addUnitOnBoard(players.get(1).getUnits().get(0));
        
        newTurn();
    }

}
