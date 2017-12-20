/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Guillaume
 */
public class Cell extends StackPane implements Serializable {

    public Board board;
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
        // Image image = new Image(getClass().getResource("grass.png").toString());
        rec.setStroke(Color.TRANSPARENT);
        rec.setHeight(Constantes.cellWidth);
        rec.setWidth(Constantes.cellWidth);
        this.getChildren().add(rec);
        Image image = new Image(getClass().getResource("ground.png").toString());
        Background bg = new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));
        this.setBackground(bg);

        //On gère l'action quand le joueur lève le click
        this.setOnMouseReleased((MouseEvent event) -> {

            /*Si une unité a bien été sélectionné, 
            on la déplace sur les cases contenues dans temp (rempli par le Drag)
            On se déplace case par case pour récupérer les collectibles sur le chemin
             */
            if (board.getCurrent_unit() != null) {
                Iterator<Cell> it = temp.iterator();
                //On gère ici le mode déplacement
                if (!board.getCurrent_player().modeAttack) {

                    while (it.hasNext()) {
                        Cell tempCell = it.next();
                        if (!board.getCurrent_unit().alreadyRecolt && tempCell.getChildren().size() > 1 && tempCell.getChildren().get(1) instanceof Resource) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("RESSOURCE !");
                            alert.setHeaderText(null);
                            alert.setContentText("Voulez vous récolter de l'or ?");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                board.getCurrent_player().gold += ((Resource) tempCell.getChildren().get(1)).collect();
                                board.getCurrent_unit().alreadyRecolt=true;
                            } else {
                                
                            }
                        }
                        board.getCurrent_unit().move(tempCell);
                    }
                } else {
                    //On gère ici le mode attaque
                    while (it.hasNext()) {
                        //On vérifie que l'unité n'a pas déjà attaqué pendant ce tour
                        if (board.getCurrent_unit().alreadyAttack) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("AU REPOS !");
                            alert.setHeaderText(null);
                            alert.setContentText("Cette unité a déjà attaqué pendant ce tour !");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                return;
                            }
                            //Si l'unité peut attaquer, on demande confirmation au joueur par une pop-up
                        } else {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("ENNEMI EN VU !");
                            alert.setHeaderText(null);
                            alert.setContentText("Un ennemi est à portée, voulez-vous les attaquer ?");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                board.getCurrent_unit().attack((Unit) it.next().getChildren().get(1));
                            } else {
                                return;
                            }

                        }
                    }
                }
            }

            //On réinitilise temp et on décolore les cases
            temp.clear();
            board.uncolorCells();

        }
        );//Fin setOnMouseReleased

        //On gère l'action quand le joueur click
        this.setOnMousePressed(
                (MouseEvent event) -> {

                    //Si il clique sur le bouton droit, on désélectionne l'unité
                    if (event.getButton() == MouseButton.SECONDARY) {
                        board.setCurrent_unit(null);
                        board.uncolorCells();
                        return;
                    }
                    //Si il clique sur une unité, on la sélectionne, et on colore les cases dans sa range de déplacement
                    if (this.getChildren().size() > 1 && this.getChildren().get(1) instanceof Unit) {
                        if (board.getCurrent_player().getUnits().contains((Unit) this.getChildren().get(1))) {
                            board.setCurrent_unit((Unit) this.getChildren().get(1));
                            if (board.getCurrent_player().modeAttack) {
                                board.getCurrent_unit().colorCellOnAttackRange();
                            } else {
                                board.getCurrent_unit().colorCellOnMoveRange();
                            }
                        }
                    }
                }
        );
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
