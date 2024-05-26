package src.main.java.units;

public class Entity {
    protected int level = 1;
    protected int experience = 1;

    protected void setLevel(int l) {
        this.level = l;
    }
    public int getLevel() {
        return this.level;
    }
    protected void setExperience(int e) {
        this.experience = e;
    }
    public int getExperience() {
        return this.experience;
    }

    public int getStrength() {
        return level * experience;
    }
    public void addExperience() {
        experience++;
    }
    public void subtractExperience() {
        experience--;
    }

    public void move() {
        experience++;

        if (experience > level * 5) {
            this.experience = experience - (level * 5);
        } else if (experience == level * 5) {
            this.experience = 1;
        }
    }
    public void log(String type) {}
    public void log(String type, int n) {}

}
