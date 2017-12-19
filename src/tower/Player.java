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

    Board board;

    public Color playerColor;
    private String name;
    private int score = 0;
    private ArrayList<Unit> units;

    //Constructeur
    public Player(String _name) {
        this.name = _name;
        units = new ArrayList();
    }

    //On augmente le score de la valeur entrée
    public void increaseScore(int by) {
        score += by;
    }

    //On attribue la bonne couleur au joueur
    public void setColorFromString(String color) {
        switch (color) {
            case "Bleu":
                playerColor = Color.BLUE;
                break;
            case "Rouge":
                playerColor = Color.RED;
                break;
            case "Vert":
                playerColor = Color.GREEN;
                break;
            case "Violet":
                playerColor = Color.PURPLE;
                break;
            case "Noir":
                playerColor = Color.BLACK;
                break;
            case "Jaune":
                playerColor = Color.YELLOW;
                break;
            default:
                playerColor = Color.ALICEBLUE;
                break;
        }
    }

    //Ajouter un grunt dans la liste des unités du joueur
    public void addGrunt() {
        units.add(new Grunt(playerColor));
    }
    
    public void addChevaucheur() {
        units.add(new Chevaucheurdeloup(playerColor));
    }

    public void addChasseur() {
        units.add(new Chasseurtroll(playerColor));
    }

    public void addFantassin() {
        units.add(new Fantassin(playerColor));
    }

    public void addChevalier() {
        units.add(new Chevalier(playerColor));
    }

    //Getters et Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public int getScore() {
        return score;
    }

}
