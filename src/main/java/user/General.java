package src.main.java.user;

import src.main.java.units.Unit;

import java.util.ArrayList;
import java.util.Random;

public class General {

    private int gold;
    private ArrayList<Unit> units;

    public General() {
        this.units = new ArrayList<Unit>();
        setGold(500);
    }


    public void setGold(int g) {
        this.gold = g;
    }
    public void giveGold(General enemy) {
        int a = (int) (gold * 0.1);
        this.gold = gold - a;

        enemy.setGold( (enemy.getGold() + a) );
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
                this.gold -= 10;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void buyUnit(int l) {
        try {
            if (this.gold < 10 * l) {
                throw new IllegalStateException("You don't have enough gold.");
            } else {
                this.units.add(new Unit(l));
                this.gold -= 10 * l;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void buyUnit(int l, int n) {
        for (int i=0; i<n; i++) {
            try {
                if (this.gold < 10 * l) {
                    throw new IllegalStateException("You don't have enough gold.");
                } else {
                    this.units.add(new Unit(l));
                    this.gold -= 10 * l;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getUnitsByLevels() {

        StringBuilder list = new StringBuilder("[");

        for (Unit unit : this.units) {
            list.append(unit.getLevel()).append(",");
        }

        list.deleteCharAt(list.length()-1);
        list.append("]");

        return list.toString();
    }

    public String getUnitsByRanks() {

        StringBuilder list = new StringBuilder("[");

        for (Unit unit : this.units) {
            list.append(unit.getLevelAsRank()).append(",");
        }

        list.deleteCharAt(list.length()-1);
        list.append("]");

        return list.toString();
    }
    public String getUnitsByRanksWithExperience() {

        StringBuilder list = new StringBuilder("[");

        for (Unit unit : this.units) {
            list.append(unit.getLevelAsRank()).append("(").append(unit.getExperience()).append(")").append(",");
        }

        list.deleteCharAt(list.length()-1);
        list.append("]");

        return list.toString();
    }

    public ArrayList<Unit> getUnits() {
        return this.units;
    }
    public int getArmySize() {
        return units.size();
    }
    public Unit getUnit(int index) {
        return units.get(index);
    }


    public void moveArmy() {

        int required = 0;
        for (Unit unit : this.units) {
            required = required + unit.getLevel();
        }

        try {
            if (this.gold < required) {
                throw new IllegalStateException(
                    "You don't have enough gold to move whole army.\n" +
                    "To move everyone you need " + required + " gold."
                );
            } else {
                for (Unit unit : this.units) {
                    unit.move();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
    public void moveArmy(int size) {
        int required = 0;
        for (int i=0; i < size ; i++) {
            required = required + units.get(i).getLevel();
        }

        try {
            if (this.gold < required) {
                throw new IllegalStateException(
                    "You don't have enough gold to move army of " + size + " units.\n" +
                    "To move " + size + " units you need " + required + " gold."
                );
            } else {
                for (int i=0; i < size ; i++) {
                    units.get(i).move();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int chooseRandom() {
        return new Random().nextInt(getArmySize() - 1 + 1) + 1;
    }
    public int getPower() {
        int power = 0;

        for (Unit unit : this.units) {
            power = power + unit.getLevel();
        }

        return power;
    }

    public void Attack(General enemy) {
        if (this.getPower() > enemy.getPower()) {

            enemy.giveGold(this);

            for (Unit unit : enemy.units) {
                unit.subtractExperience();
            }
            for (Unit unit : this.units) {
                unit.addExperience();
            }

            enemy.units.removeIf(unit -> unit.getExperience() <= 0);

        } else if (this.getPower() == enemy.getPower()) {
            this.units.remove(this.chooseRandom());
            enemy.units.remove(enemy.chooseRandom());
        } else {

            this.giveGold(enemy);

            for (Unit unit : this.units) {
                unit.subtractExperience();
            }
            for (Unit unit : enemy.units) {
                unit.addExperience();
            }

            this.units.removeIf(unit -> unit.getExperience() <= 0);
        }
    }
}
