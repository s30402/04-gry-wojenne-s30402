package src.main.java.user;

import src.main.java.units.Unit;

import java.util.ArrayList;

public class General {

    private int gold = 500;
    private ArrayList<Unit> units;

    public void setGold(int g) {
        this.gold = g;
    }
    public int getGold() {
        return gold;
    }

    public void buyUnit() {
        try {
            if (this.gold < 10) {
                throw new IllegalStateException("You don't have enough gold.");
            } else {
                this.units.add(new Unit());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void buyUnit(int l, int n) {
        for (int i=0; i<n; i++) {
            try {
                if (this.gold < 10) {
                    throw new IllegalStateException("You don't have enough gold.");
                } else {
                    this.units.add(new Unit(l));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int getArmySize() {
        return units.size();
    }
    public Unit getUnit(int index) {
        return units.get(index);
    }


    public void moveArmy() {
        for (Unit unit : this.units) {
            unit.move();
        }
    }
    public void moveArmy(int size) {
        for (int i=0; i < size ; i++) {
            units.get(i).move();
        }
    }
}
