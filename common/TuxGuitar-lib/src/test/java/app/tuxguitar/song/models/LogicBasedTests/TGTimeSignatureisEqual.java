package app.tuxguitar.song.models.LogicBasedTests;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import app.tuxguitar.song.factory.TGFactory;
import app.tuxguitar.song.models.TGTimeSignature;

public class TGTimeSignatureIsEqualTest {

    private TGTimeSignature makeTimeSignature(TGFactory factory, int numerator, int denominatorValue) {
        TGTimeSignature ts = factory.newTimeSignature();
        ts.setNumerator(numerator);
        ts.getDenominator().setValue(denominatorValue);
        return ts;
    }

    @Test
    public void testIsEqual_BothMatch() {
        TGFactory factory = new TGFactory();
        TGTimeSignature ts1 = makeTimeSignature(factory, 4, 4);
        TGTimeSignature ts2 = makeTimeSignature(factory, 4, 4);
        assertTrue(ts1.isEqual(ts2));
    }

    @Test
    public void testIsEqual_NumeratorsDiffer() {
        TGFactory factory = new TGFactory();
        TGTimeSignature ts1 = makeTimeSignature(factory, 4, 4);
        TGTimeSignature ts2 = makeTimeSignature(factory, 3, 4);
        assertFalse(ts1.isEqual(ts2));
    }

    @Test
    public void testIsEqual_DenominatorsDiffer() {
        TGFactory factory = new TGFactory();
        TGTimeSignature ts1 = makeTimeSignature(factory, 4, 4);
        TGTimeSignature ts2 = makeTimeSignature(factory, 4, 8);
        assertFalse(ts1.isEqual(ts2));
    }

    @Test
    public void testIsEqual_BothDiffer() {
        TGFactory factory = new TGFactory();
        TGTimeSignature ts1 = makeTimeSignature(factory, 4, 4);
        TGTimeSignature ts2 = makeTimeSignature(factory, 3, 8);
        assertFalse(ts1.isEqual(ts2));
    }
}
