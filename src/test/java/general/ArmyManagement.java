package src.test.java.general;

import org.junit.Test;
import src.main.java.user.General;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ArmyManagement {

    @Test
    public void addNewUnit() {
        General general = new General();

        general.buyUnit(1, 10);

        System.out.println(general.getUnits());

    }

}
