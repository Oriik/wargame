/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Guillaume
 */
public class Player implements Serializable {

    transient Board board;

    public String faction;
    public String name;
    public int gold;
    public int mana;
    public int score = 0;
    public ArrayList<Unit> units;
    public boolean modeAttack;

    //Constructeur
    public Player(String _name, String _faction, Board _board) {
        this.name = _name;
        units = new ArrayList();
        this.faction = _faction;
        this.gold = 100;
        this.mana = 0;
        this.board=_board;
    }

    //On augmente le score de la valeur entrée
    public void increaseScore(int by) {
        score += by;
    }

    //Ajouter un grunt dans la liste des unités du joueur
    public void addWarrior() {
        Warrior temp = new Warrior(faction);
        units.add(temp);
        board.addUnitOnBoard(temp);
    }

    public void addHorseman() {
        Horseman temp = new Horseman(faction);
        units.add(temp);
        board.addUnitOnBoard(temp);
    }

    public void addBowman() {
        Bowman temp = new Bowman(faction);
        units.add(temp);
        board.addUnitOnBoard(temp);
    }

    /* public void addFantassin() {
        units.add(new Fantassin(playerColor));
    }

    public void addChevalier() {
        units.add(new Chevalier(playerColor));
    }*/
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
