/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tower.Events.UnitPickedEvent;
import tower.Events.UnitUnpickedEvent;

/**
 *
 * @author Guillaume
 */
public class Cell extends StackPane {

    Board board;
    public static ArrayList<Cell> temp = new ArrayList();
    
    //Constructeur
    public Cell(Board board) {
        this.board = board;
    }
    
    /*
    Fonction d'initialisation
    Utile car certaines fonctions lèvent des Warnings si utilisées dans le constructeur 
     */
    public void initCell() {

        //On créé un rectangle qui va servir de "fond" de notre case
        Rectangle rec = new Rectangle();
        rec.setFill(Color.TRANSPARENT);
        rec.setStroke(Color.BLACK);
        rec.setHeight(Constantes.cellWidth);
        rec.setWidth(Constantes.cellWidth);
        this.getChildren().add(rec);

        //On gère l'action quand le joueur lève le click
        this.setOnMouseReleased((MouseEvent event) -> {

            /*Si une unité a bien été sélectionné, 
            on la déplace sur les cases contenues dans temp (rempli par le Drag)
            On se déplace case par case pour récupérer les collectibles sur le chemin
            */
            if (board.getCurrent_unit() != null) {
                Iterator<Cell> it = temp.iterator();
                while (it.hasNext()) {
                    board.getCurrent_unit().move(it.next());
                }
            }
            //On réinitilise temp et on décolore les cases
            temp.clear();
            board.uncolorCells();

        });//Fin setOnMouseReleased

        //On gère l'action quand le joueur click
        this.setOnMousePressed((MouseEvent event) -> {

            //Si il clique sur le bouton droit, on désélectionne l'unité
            if (event.getButton() == MouseButton.SECONDARY) {
                board.setCurrent_unit(null);
                board.uncolorCells();
                UnitUnpickedEvent eventTemp = new UnitUnpickedEvent();
                this.fireEvent(eventTemp);
                return;
            }
            //Si il clique sur une unité, on la sélectionne, et on colore les cases dans sa range de déplacement
            if (this.getChildren().size() > 1 && this.getChildren().get(1) instanceof Unit) {
                if (board.getCurrent_player().getUnits().contains((Unit) this.getChildren().get(1))) {
                    board.setCurrent_unit((Unit) this.getChildren().get(1));
                    board.getCurrent_unit().colorCellOnRange();
                    UnitPickedEvent eventTemp = new UnitPickedEvent();
                    this.fireEvent(eventTemp);
                }
            }
        });
    }

 
    //Permet de colorer la case
    public void color(Color color) {
        ((Rectangle) this.getChildren().get(0)).setFill(color);
    }

    //Fonction pour raccourcir l'écriture
    public Node get(int i) {
        return this.getChildren().get(i);
    }
}
