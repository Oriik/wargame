/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.util.ArrayList;

/**
 *
 * @author Guillaume
 */
public class Player {

    Board board;

    public String faction;
    private String name;
    public int wood;
    public int gold;
    public int mana;
    private int score = 0;
    private ArrayList<Unit> units;
    public boolean modeAttack;

    //Constructeur
    public Player(String _name, String _faction) {
        this.name = _name;
        units = new ArrayList();
        this.faction = _faction;
        this.wood= 100;
        this.gold = 100;
        this.mana = 0;
    }

    //On augmente le score de la valeur entrée
    public void increaseScore(int by) {
        score += by;
    }

    //Ajouter un grunt dans la liste des unités du joueur
    public void addWarrior() {
        units.add(new Warrior(faction));
    }

    public void addHorseman() {
        units.add(new Horseman(faction));
    }

    public void addBowman() {
        units.add(new Bowman(faction));
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
