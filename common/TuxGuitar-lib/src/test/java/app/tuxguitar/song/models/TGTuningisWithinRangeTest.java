import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import app.tuxguitar.song.models.TGTuning;

public class TGTuningIsWithinRangeTest {

    private TGTuning makeTuning(int[] values) {
        TGTuning t = new TGTuning();
        t.setValues(values);
        return t;
    }

    @Test
    public void testIsWithinRange_EmptyArray() {
        TGTuning t = makeTuning(new int[]{});
        assertFalse(t.isWithinRange(40, 60));
    }

    @Test
    public void testIsWithinRange_BothInRange() {
        TGTuning t = makeTuning(new int[]{40, 45, 50, 55, 60});
        assertTrue(t.isWithinRange(40, 60));
    }

    @Test
    public void testIsWithinRange_MaxTooHigh() {
        TGTuning t = makeTuning(new int[]{40, 45, 50, 55, 60});
        assertFalse(t.isWithinRange(40, 61));
    }

    @Test
    public void testIsWithinRange_MinTooLow() {
        TGTuning t = makeTuning(new int[]{40, 45, 50, 55, 60});
        assertFalse(t.isWithinRange(39, 60));
    }

    @Test
    public void testIsWithinRange_BothOutOfRange() {
        TGTuning t = makeTuning(new int[]{40, 45, 50, 55, 60});
        assertFalse(t.isWithinRange(39, 61));
    }
}
