package src.test.java.general;

import org.junit.Test;
import src.main.java.user.General;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ArmyManagement {

    @Test
    public void addNewUnit() {
        General general = new General();
        general.buyUnit();

        assertEquals("[Private]", general.getUnitsByRanks());
    }

    @Test
    public void addNewUnitOneArgument() {
        General general = new General();
        general.buyUnit(4);

        assertEquals("[Major]", general.getUnitsByRanks());
    }

    @Test
    public void addNewUnitTwoArguments() {
        General general = new General();
        general.buyUnit(4,2);

        assertEquals("[Major,Major]", general.getUnitsByRanks());
    }

    @Test
    public void addNewUnitsWithTwoArguments() {
        General general = new General();

        general.buyUnit(4,2);
        general.buyUnit(3,1);

        assertEquals("[Major,Major,Captain]", general.getUnitsByRanks());
    }

    @Test
    public void moveArmy() {
        General general = new General();

        general.buyUnit(4,2);
        general.buyUnit(3,1);

        assertEquals("[Major(1),Major(1),Captain(1)]", general.getUnitsByRanksWithExperience());

        general.moveArmy();

        assertEquals("[Major(2),Major(2),Captain(2)]", general.getUnitsByRanksWithExperience());
    }

    @Test
    public void testPower() {
        General general = new General();

        general.buyUnit(4,2);
        general.buyUnit(3,1);

        assertEquals(11, general.getPower());
    }

    @Test
    public void levelUnit() {
        General general = new General();

        general.buyUnit();
        general.getUnit(0).setExperience(4);

        general.moveArmy();

        assertEquals("[Corporal(1)]", general.getUnitsByRanksWithExperience());
    }

    @Test
    public void levelUnitOverlimit() {
        General general = new General();

        general.buyUnit();
        general.getUnit(0).setExperience(6);

        general.moveArmy();

        assertEquals("[Corporal(3)]", general.getUnitsByRanksWithExperience());
    }
}
