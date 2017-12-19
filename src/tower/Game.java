/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.util.ArrayList;
import javafx.event.EventType;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import static tower.Constantes.boardHeight;
import static tower.Constantes.boardWidth;
import static tower.Constantes.cellWidth;

/**
 *
 * @author Guillaume
 */
public class Game extends BorderPane {

    public Board board;
    public Menu menu;
    public ArrayList<Player> players;
    public ArrayList<Collectible> collectibles;
    public ScrollPane scroll;
    public InventoryPane inventory;

    /*
    Fonction d'initialisation
    Utile car certaines fonctions lèvent des Warnings si utilisées dans le constructeur 
     */
    public void initGame() {
        //On créé les deux joueurs, avec une valeur par défaut pour leurs noms
        players = new ArrayList();
        Player player1 = new Player("Joueur 1", "human");
        Player player2 = new Player("Joueur 2", "orc");
        players.add(player1);
        players.add(player2);
        collectibles = new ArrayList();
        for (int i = 0; i < boardWidth; i++) {
            collectibles.add(new Collectible());
        }

        board = new Board(players, collectibles);
        board.initialize();
        menu = new Menu();
        menu.initMenu();

        //On créé notre ScrollPane avec les bonnes caractéristiques pour avoir une grande map qui bouge dans notre fenetre
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        scroll = new ScrollPane();
        board.setPrefWidth(screenSize.getWidth() * 0.80);
        scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollBarPolicy.NEVER);
        scroll.setContent(board);

        //TODO : Un inventaire en haut de l'écran avec le nombre de ressources du joueur actuel
        inventory = new InventoryPane();
        this.setTop(inventory);
        this.setCenter(scroll);
        this.setRight(menu);

        //Tous les évènements seront géré par une instance de la classe MyEventHandler
        this.addEventHandler(EventType.ROOT, new MyEventHandler(menu, board));
    }

    //Bouge le ScrollPane sur l'unité sélectionné
    public void focusCameraOnUnit(Unit unit) {
        scroll.setHvalue(unit.getParent().getLayoutX() / (boardHeight * (cellWidth + 1)));
        scroll.setVvalue(unit.getParent().getLayoutY() / (boardWidth * (cellWidth + 1)));
    }

    public void newTurn() {
        board.newTurn();
        menu.newTurn(board.getCurrent_player());
        inventory.refresh(board.getCurrent_player().wood, board.getCurrent_player().gold, board.getCurrent_player().mana);
        //On "centre" la grille sur une unité du joueur
        focusCameraOnUnit(board.getCurrent_player().getUnits().get(0));

    }

    //On lance le jeu, fonction appelé après l'écran Home
    void start(String player1Name, String player2Name) {
        //On nomme les joueurs, si ils ont saisie un nom
        if (!"".equals(player1Name)) {
            players.get(0).setName(player1Name);
        }
        if (!"".equals(player2Name)) {
            players.get(1).setName(player2Name);
        }
        //On créé une unité pour chaque joueur pour commencer la partie
        players.get(0).addWarrior();
        players.get(0).addBowman();
        players.get(0).addHorseman();
        players.get(1).addWarrior();
        players.get(1).addBowman();
        players.get(1).addHorseman();
        board.addUnitOnBoard(players.get(0).getUnits().get(0));
        board.addUnitOnBoard(players.get(1).getUnits().get(0));
        board.addUnitOnBoard(players.get(0).getUnits().get(1));
        board.addUnitOnBoard(players.get(1).getUnits().get(1));
        board.addUnitOnBoard(players.get(0).getUnits().get(2));
        board.addUnitOnBoard(players.get(1).getUnits().get(2));

        newTurn();
    }

    void addUnitOnBoard(Unit unit) {
        board.addUnitOnBoard(unit);
    }

}
