import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import app.tuxguitar.song.models.TGTuning;

public class TGTuningCompareToTest {

    @Test
    public void testCompareTo_BothNull() {
        TGTuning t1 = new TGTuning();
        TGTuning t2 = new TGTuning();
        assertEquals(0, t1.compareTo(t2));
    }

    @Test
    public void testCompareTo_ThisNullOtherHasPriority() {
        TGTuning t1 = new TGTuning();
        TGTuning t2 = new TGTuning();
        t2.setPriority(1);
        assertEquals(1, t1.compareTo(t2));
    }

    @Test
    public void testCompareTo_ThisHasPriorityOtherNull() {
        TGTuning t1 = new TGTuning();
        t1.setPriority(1);
        TGTuning t2 = new TGTuning();
        assertEquals(-1, t1.compareTo(t2));
    }

    @Test
    public void testCompareTo_BothHavePriority_ThisLower() {
        TGTuning t1 = new TGTuning();
        t1.setPriority(2);
        TGTuning t2 = new TGTuning();
        t2.setPriority(5);
        assertEquals(-3, t1.compareTo(t2));
    }

    @Test
    public void testCompareTo_BothHavePriority_ThisHigher() {
        TGTuning t1 = new TGTuning();
        t1.setPriority(5);
        TGTuning t2 = new TGTuning();
        t2.setPriority(2);
        assertEquals(3, t1.compareTo(t2));
    }

    @Test
    public void testCompareTo_BothHavePriority_Equal() {
        TGTuning t1 = new TGTuning();
        t1.setPriority(3);
        TGTuning t2 = new TGTuning();
        t2.setPriority(3);
        assertEquals(0, t1.compareTo(t2));
    }
}
