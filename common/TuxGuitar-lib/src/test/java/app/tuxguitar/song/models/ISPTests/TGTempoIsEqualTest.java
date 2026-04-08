package app.tuxguitar.song.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TGTempoIsEqualTest {

    private static class TestTGTempo extends TGTempo { }

    @Test
    public void testAllFieldsEqualReturnsTrue() {
        TestTGTempo t1 = new TestTGTempo();
        TestTGTempo t2 = new TestTGTempo();

        t1.setValueBase(120, TGDuration.QUARTER, false);
        t2.setValueBase(120, TGDuration.QUARTER, false);

        assertTrue(t1.isEqual(t2));
    }

    @Test
    public void testDifferentRawValueReturnsFalse() {
        TestTGTempo t1 = new TestTGTempo();
        TestTGTempo t2 = new TestTGTempo();

        t1.setValueBase(120, TGDuration.QUARTER, false);
        t2.setValueBase(100, TGDuration.QUARTER, false);

        assertFalse(t1.isEqual(t2));
    }

    @Test
    public void testDifferentBaseReturnsFalse() {
        TestTGTempo t1 = new TestTGTempo();
        TestTGTempo t2 = new TestTGTempo();

        t1.setValueBase(120, TGDuration.QUARTER, false);
        t2.setValueBase(120, TGDuration.EIGHTH, false);

        assertFalse(t1.isEqual(t2));
    }

    @Test
    public void testDifferentDottedReturnsFalse() {
        TestTGTempo t1 = new TestTGTempo();
        TestTGTempo t2 = new TestTGTempo();

        t1.setValueBase(120, TGDuration.QUARTER, false);
        t2.setValueBase(120, TGDuration.QUARTER, true);

        assertFalse(t1.isEqual(t2));
    }
}
