package src.main.java.user;

import src.main.java.ReportManager;
import src.main.java.units.Entity;
import src.main.java.units.Unit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class General implements Serializable {

    private int gold;
    private String name;
    private ArrayList<Unit> units;

    public General() {
        this.units = new ArrayList<Unit>();
        setGold(500);
    }

    public General(String name) {
        this.units = new ArrayList<Unit>();
        setGold(500);
        setName(name);
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

    public void setName(String name) {
        this.name = name;
    }
    public String getName() { return this.name; }

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
                    ReportManager.log(this, unit, ReportManager.Action.GAIN_EXPERIENCE);
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
                    ReportManager.log(this, i, ReportManager.Action.GAIN_EXPERIENCE);
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
                if (unit.getExperience() <= 0) {
                    ReportManager.log(enemy, unit, ReportManager.Action.DIED_IN_ACTION);
                } else {
                    ReportManager.log(enemy, unit, ReportManager.Action.LOST_EXPERIENCE);
                }
            }
            for (Unit unit : this.units) {
                unit.addExperience();
                ReportManager.log(this, unit, ReportManager.Action.GAIN_EXPERIENCE);
            }

            enemy.units.removeIf(unit -> unit.getExperience() <= 0);

        } else if (this.getPower() == enemy.getPower()) {
            int t = this.chooseRandom();
            int e = enemy.chooseRandom();

            ReportManager.log(this, t, ReportManager.Action.DIED_IN_ACTION);
            ReportManager.log(this, e, ReportManager.Action.DIED_IN_ACTION);

            this.units.remove(t);
            enemy.units.remove(e);
        } else {

            this.giveGold(enemy);

            for (Unit unit : this.units) {
                unit.subtractExperience();
                if (unit.getExperience() <= 0) {
                    ReportManager.log(this, unit, ReportManager.Action.DIED_IN_ACTION);
                } else {
                    ReportManager.log(this, unit, ReportManager.Action.LOST_EXPERIENCE);
                }
            }
            for (Unit unit : enemy.units) {
                unit.addExperience();
                ReportManager.log(enemy, unit, ReportManager.Action.GAIN_EXPERIENCE);
            }

            this.units.removeIf(unit -> unit.getExperience() <= 0);
        }
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Gold: ").append(gold).append("\n");
        sb.append("Units: \n");
        for (Unit unit : units) {
            sb.append(unit.toString()).append("\n");
        }
        return sb.toString();
    }
}
