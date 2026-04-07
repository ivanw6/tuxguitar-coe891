package app.tuxguitar.song.models;

import static org.junit.Assert.*;
import org.junit.Test;

public class TGTempoGetTicksInMillisTest {

    private static class TestTGTempo extends TGTempo { }

    @Test
    public void testZeroTicksReturnsZero() {
        TestTGTempo tempo = new TestTGTempo();

        assertEquals(0L, tempo.getTicksInMillis(0));
    }

    @Test
    public void testOneQuarterNoteTicksAtDefaultTempo() {
        TestTGTempo tempo = new TestTGTempo();

        assertEquals(500L, tempo.getTicksInMillis(TGDuration.QUARTER_TIME));
    }

    @Test
    public void testTwoQuarterNotesTicksAtDefaultTempo() {
        TestTGTempo tempo = new TestTGTempo();

        assertEquals(1000L, tempo.getTicksInMillis(TGDuration.QUARTER_TIME * 2));
    }
}
