package app.tuxguitar.song.models;

import static org.junit.Assert.*;
import org.junit.Test;

public class TGTempoSetValueBaseTest {

    private static class TestTGTempo extends TGTempo { }

    @Test
    public void testQuarterNotDotted() {
        TestTGTempo tempo = new TestTGTempo();

        tempo.setValueBase(120, TGDuration.QUARTER, false);

        assertEquals(120, tempo.getQuarterValue());
        assertEquals(120, tempo.getRawValue());
        assertEquals(TGDuration.QUARTER, tempo.getBase());
        assertFalse(tempo.isDotted());
    }

    @Test
    public void testQuarterDotted() {
        TestTGTempo tempo = new TestTGTempo();

        tempo.setValueBase(120, TGDuration.QUARTER, true);

        assertEquals(180, tempo.getQuarterValue());
        assertEquals(120, tempo.getRawValue());
        assertEquals(TGDuration.QUARTER, tempo.getBase());
        assertTrue(tempo.isDotted());
    }

    @Test
    public void testNonQuarterNotDotted() {
        TestTGTempo tempo = new TestTGTempo();

        tempo.setValueBase(120, TGDuration.EIGHTH, false);

        assertEquals(60, tempo.getQuarterValue());
        assertEquals(120, tempo.getRawValue());
        assertEquals(TGDuration.EIGHTH, tempo.getBase());
        assertFalse(tempo.isDotted());
    }
}
