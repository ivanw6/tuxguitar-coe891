package app.tuxguitar.song.models.LogicBasedTests;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import app.tuxguitar.song.factory.TGFactory;
import app.tuxguitar.song.models.TGDuration;

public class TGDurationIsEqualTest {

    private TGDuration makeBase(TGFactory factory) {
        TGDuration d = factory.newDuration();
        d.setValue(TGDuration.QUARTER);
        d.setDotted(false);
        d.setDoubleDotted(false);
        d.getDivision().setEnters(1);
        d.getDivision().setTimes(1);
        return d;
    }

    @Test
    public void testIsEqual_AllMatch() {
        TGFactory factory = new TGFactory();
        TGDuration d1 = makeBase(factory);
        TGDuration d2 = makeBase(factory);
        assertTrue(d1.isEqual(d2));
    }

    @Test
    public void testIsEqual_DivisionDiffers() {
        TGFactory factory = new TGFactory();
        TGDuration d1 = makeBase(factory);
        TGDuration d2 = makeBase(factory);
        d2.getDivision().setEnters(3);
        d2.getDivision().setTimes(2);
        assertFalse(d1.isEqual(d2));
    }

    @Test
    public void testIsEqual_DoubleDottedDiffers() {
        TGFactory factory = new TGFactory();
        TGDuration d1 = makeBase(factory);
        TGDuration d2 = makeBase(factory);
        d2.setDoubleDotted(true);
        assertFalse(d1.isEqual(d2));
    }

    @Test
    public void testIsEqual_DoubleDottedAndDivisionDiffer() {
        TGFactory factory = new TGFactory();
        TGDuration d1 = makeBase(factory);
        TGDuration d2 = makeBase(factory);
        d2.setDoubleDotted(true);
        d2.getDivision().setEnters(3);
        d2.getDivision().setTimes(2);
        assertFalse(d1.isEqual(d2));
    }

    @Test
    public void testIsEqual_DottedDiffers() {
        TGFactory factory = new TGFactory();
        TGDuration d1 = makeBase(factory);
        TGDuration d2 = makeBase(factory);
        d2.setDotted(true);
        assertFalse(d1.isEqual(d2));
    }

    @Test
    public void testIsEqual_DottedAndDivisionDiffer() {
        TGFactory factory = new TGFactory();
        TGDuration d1 = makeBase(factory);
        TGDuration d2 = makeBase(factory);
        d2.setDotted(true);
        d2.getDivision().setEnters(3);
        d2.getDivision().setTimes(2);
        assertFalse(d1.isEqual(d2));
    }

    @Test
    public void testIsEqual_ValueDiffers() {
        TGFactory factory = new TGFactory();
        TGDuration d1 = makeBase(factory);
        TGDuration d2 = makeBase(factory);
        d2.setValue(TGDuration.EIGHTH);
        assertFalse(d1.isEqual(d2));
    }

    @Test
    public void testIsEqual_ValueAndDivisionDiffer() {
        TGFactory factory = new TGFactory();
        TGDuration d1 = makeBase(factory);
        TGDuration d2 = makeBase(factory);
        d2.setValue(TGDuration.EIGHTH);
        d2.getDivision().setEnters(3);
        d2.getDivision().setTimes(2);
        assertFalse(d1.isEqual(d2));
    }
}
