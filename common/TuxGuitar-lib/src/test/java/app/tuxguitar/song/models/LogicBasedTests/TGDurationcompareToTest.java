package app.tuxguitar.song.models.LogicBasedTests;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import app.tuxguitar.song.factory.TGFactory;
import app.tuxguitar.song.models.TGDuration;

public class TGDurationCompareToTest {

    @Test
    public void testCompareTo_Null() {
        TGFactory factory = new TGFactory();
        TGDuration d = factory.newDuration();
        d.setValue(TGDuration.QUARTER);
        assertEquals(1, d.compareTo(null));
    }

    @Test
    public void testCompareTo_ThisShorter() {
        TGFactory factory = new TGFactory();
        TGDuration d1 = factory.newDuration();
        d1.setValue(TGDuration.EIGHTH);
        TGDuration d2 = factory.newDuration();
        d2.setValue(TGDuration.QUARTER);
        assertTrue(d1.compareTo(d2) < 0);
    }

    @Test
    public void testCompareTo_Equal() {
        TGFactory factory = new TGFactory();
        TGDuration d1 = factory.newDuration();
        d1.setValue(TGDuration.QUARTER);
        TGDuration d2 = factory.newDuration();
        d2.setValue(TGDuration.QUARTER);
        assertEquals(0, d1.compareTo(d2));
    }

    @Test
    public void testCompareTo_ThisLonger() {
        TGFactory factory = new TGFactory();
        TGDuration d1 = factory.newDuration();
        d1.setValue(TGDuration.HALF);
        TGDuration d2 = factory.newDuration();
        d2.setValue(TGDuration.QUARTER);
        assertTrue(d1.compareTo(d2) > 0);
    }
}
