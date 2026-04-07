package app.tuxguitar.song.models;

import static org.junit.Assert.*;
import org.junit.Test;

public class TGTempoGetInMillisTest {

    private static class TestTGTempo extends TGTempo { }

    @Test
    public void testDefaultTempoReturns500Millis() {
        TestTGTempo tempo = new TestTGTempo();

        assertEquals(500L, tempo.getInMillis());
    }

    @Test
    public void testFasterTempoReturnsSmallerMillis() {
        TestTGTempo tempo = new TestTGTempo();
        tempo.setValueBase(240, TGDuration.QUARTER, false);

        assertEquals(250L, tempo.getInMillis());
    }

    @Test
    public void testDottedTempoUsesUpdatedQuarterValue() {
        TestTGTempo tempo = new TestTGTempo();
        tempo.setValueBase(120, TGDuration.QUARTER, true);

        assertEquals(333L, tempo.getInMillis());
    }
}
