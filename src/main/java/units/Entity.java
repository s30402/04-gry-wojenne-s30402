package src.main.java.units;

import java.io.Serializable;

public class Entity implements Serializable {
    protected int level = 1;
    protected int experience = 1;

    protected void setLevel(int l) {
        this.level = l;
    }
    public int getLevel() {
        return this.level;
    }
    public String getLevelAsRank() {
        return switch (this.level) {
            case 1 -> "Private";
            case 2 -> "Corporal";
            case 3 -> "Captain";
            case 4 -> "Major";
            default -> "???";
        };
    }

    public void setExperience(int e) {
        this.experience = e;
    }
    public int getExperience() {
        return this.experience;
    }
    public void addExperience() {
        experience++;
    }
    public void subtractExperience() {
        experience--;
    }

    public int getStrength() {
        return level * experience;
    }

    public void move() {
        experience++;

        if (experience > level * 5) {
            this.level++;
            this.experience = (level * 5) - experience;
        } else if (experience == level * 5) {
            this.level++;
            this.experience = 1;
        }
    }
    public String toString() {
        return "Rank: " + this.getLevelAsRank() + ", Experience: " + experience;
    }

}
