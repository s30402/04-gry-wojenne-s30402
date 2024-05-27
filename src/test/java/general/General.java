package src.test.java.general;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class General {

    @Test
    public void attackEnemyWithLowerPower_ReceiveGold() {
        src.main.java.user.General general = new src.main.java.user.General();
        general.buyUnit(1,10);
        general.buyUnit(2,2);

        System.out.println("General 1 power: " + general.getPower());
        System.out.println("General 1 gold: " + general.getGold() + "\n");

        src.main.java.user.General general2 = new src.main.java.user.General();
        general2.buyUnit(1,10);

        System.out.println("General 2 power: " + general2.getPower());
        System.out.println("General 2 gold: " + general2.getGold() + "\n");

        general.Attack(general2);

        assertEquals(400, general.getGold());
        assertEquals(360, general2.getGold());
    }

    @Test
    public void attackEnemyWithHigherPower_ReceiveGold() {
        src.main.java.user.General general = new src.main.java.user.General();
        general.buyUnit(1,10);

        System.out.println("General 1 power: " + general.getPower());
        System.out.println("General 1 gold: " + general.getGold() + "\n");

        src.main.java.user.General general2 = new src.main.java.user.General();
        general2.buyUnit(1,10);
        general2.buyUnit(2,2);

        System.out.println("General 2 power: " + general2.getPower());
        System.out.println("General 2 gold: " + general2.getGold() + "\n");

        general.Attack(general2);

        assertEquals(360, general.getGold());
        assertEquals(400, general2.getGold());
    }

}
