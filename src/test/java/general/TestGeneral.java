package src.test.java.general;

import org.junit.Test;
import src.main.java.ReportManager;
import src.main.java.user.General;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestGeneral {

    @Test
    public void attackEnemyWithLowerPower_ReceiveGold() {
        General general = new General();
        general.buyUnit(1,10);
        general.buyUnit(2,2);

        System.out.println("General 1 power: " + general.getPower());
        System.out.println("General 1 gold: " + general.getGold() + "\n");

        General general2 = new General();
        general2.buyUnit(1,10);

        System.out.println("General 2 power: " + general2.getPower());
        System.out.println("General 2 gold: " + general2.getGold() + "\n");

        general.Attack(general2);

        assertEquals(400, general.getGold());
        assertEquals(360, general2.getGold());
    }

    @Test
    public void attackEnemyWithHigherPower_ReceiveGold() {
        General general = new General();
        general.buyUnit(1,10);

        System.out.println("General 1 power: " + general.getPower());
        System.out.println("General 1 gold: " + general.getGold() + "\n");

        General general2 = new General();
        general2.buyUnit(1,10);
        general2.buyUnit(2,2);

        System.out.println("General 2 power: " + general2.getPower());
        System.out.println("General 2 gold: " + general2.getGold() + "\n");

        general.Attack(general2);

        assertEquals(360, general.getGold());
        assertEquals(400, general2.getGold());
    }

    @Test
    public void testReportGeneration() {
        General general1 = new General("Gson");
        general1.buyUnit(1,10);

        General general2 = new General("Json");
        general2.buyUnit(2, 4);

        general1.moveArmy();
        for (int i=0; i<3; i++) {
            general2.moveArmy();
        }

        general1.Attack(general2);
    }

}
