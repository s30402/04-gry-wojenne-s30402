package src.test.java.general;

import org.junit.Test;
import src.main.java.user.General;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestAttack {

    @Test
    public void attackEnemyWithLowerPower() {
        General general = new General();
        general.buyUnit(1,10);
        general.buyUnit(2,2);

        System.out.println("General 1 power: " + general.getPower());

        General general2 = new General();
        general2.buyUnit(1,10);

        System.out.println("General 2 power: " + general2.getPower());

        general.Attack(general2);

        assertEquals(0, general2.getPower());
    }

    @Test
    public void attackEnemyWithHigherPower() {
        General general = new General();
        general.buyUnit(1,10);
        general.buyUnit(2,2);

        System.out.println("General 1 power: " + general.getPower());

        General general2 = new General();
        general2.buyUnit(1,10);
        general2.buyUnit(4,2);

        System.out.println("General 2 power: " + general2.getPower());

        general.Attack(general2);

        assertEquals(0, general.getPower());
    }

    @Test
    public void attackEnemyWithSimilarPower() {
        General general = new General();
        general.buyUnit(1,10);

        System.out.println("General 1 power: " + general.getPower());

        General general2 = new General();
        general2.buyUnit(1,10);

        System.out.println("General 2 power: " + general2.getPower());

        general.Attack(general2);

        assertEquals(9, general.getPower());
        assertEquals(9, general2.getPower());
    }

}
