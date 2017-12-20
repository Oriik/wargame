/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tower;

import java.io.Serializable;
import java.util.ArrayList;
import static tower.Constantes.bowmanPrice;
import static tower.Constantes.horsemanPrice;
import static tower.Constantes.manaDrop;
import static tower.Constantes.rateManaGold;
import static tower.Constantes.warriorPrice;
import static tower.Constantes.win;

/**
 *
 * @author Guillaume
 */
public class Player implements Serializable {

    //Transcient indique que board ne doit pas être sérialisé lors de la sauvegarde
    transient Board board;

    public String faction;
    public String name;
    public int gold;
    public int mana;
    public int score = 0;
    public ArrayList<Unit> units;
    public ArrayList<Building> buildings;
    public boolean modeAttack;
    public boolean alreadyDropMana;
    public ArrayList<Unit> waiting;

    //Constructeur
    public Player(String _name, String _faction, Board _board) {
        this.name = _name;
        units = new ArrayList();
        buildings = new ArrayList();
        this.faction = _faction;
        this.gold = 100;
        this.mana = 1000;
        this.board = _board;
        this.waiting = new ArrayList();
        this.alreadyDropMana=false;
    }



    //Ajouter un grunt dans la liste des unités du joueur
    public void addWarrior() {
        Warrior temp = new Warrior(faction);
        waiting.add(temp);
    }

    public void addHorseman() {
        Horseman temp = new Horseman(faction);
        waiting.add(temp);
    }

    public void addBowman() {
        Bowman temp = new Bowman(faction);
        waiting.add(temp);
    }

    public void addCastle() {
        Castle temp = new Castle(faction);
        buildings.add(temp);
        board.addCastleOnBoard(temp);
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


    void buyWarrior(String type) {
        if (type.compareTo("gold") == 0) {
            if (gold >= warriorPrice) {
                addWarrior();
                gold -= warriorPrice;
            }
        }
        if (type.compareTo("mana") == 0) {
            if (mana >= warriorPrice/rateManaGold) {
                addWarrior();
                mana-= warriorPrice/rateManaGold;
            }
        }

    }

    void buyBowman(String type) {
        if (type.compareTo("gold") == 0) {
            if (gold >= bowmanPrice) {
                addBowman();
                gold -= bowmanPrice;
            }
        }
        if (type.compareTo("mana") == 0) {
            if (mana >= bowmanPrice/rateManaGold) {
                addBowman();
                mana-= bowmanPrice/rateManaGold;
            }
        }
    }

    void buyHorseman(String type) {
        if (type.compareTo("gold") == 0) {
            if (gold >= horsemanPrice) {
                addHorseman();
                gold -= horsemanPrice;
            }
        }
        if (type.compareTo("mana") == 0) {
            if (mana >= horsemanPrice/rateManaGold) {
                addHorseman();
                mana-= horsemanPrice/rateManaGold;
            }
        }
    }

    void dropMana() {
         if (!this.alreadyDropMana) {
             if(this.mana>=manaDrop){
                 mana-=manaDrop;
                 alreadyDropMana=true;
                 score++;
                 if(score>=win){
                     EndGameAlert alert = new EndGameAlert();
                 }
             }
         }
    }

}
