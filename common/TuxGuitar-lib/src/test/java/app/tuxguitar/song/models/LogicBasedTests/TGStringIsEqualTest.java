package app.tuxguitar.song.models.LogicBasedTests;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import app.tuxguitar.song.models.TGString;

public class TGStringIsEqualTest {

    private TGString makeString(int number, int value) {
        TGString s = new TGString() {};
        s.setNumber(number);
        s.setValue(value);
        return s;
    }

    @Test
    public void testIsEqual_BothMatch() {
        TGString s1 = makeString(1, 40);
        TGString s2 = makeString(1, 40);
        assertTrue(s1.isEqual(s2));
    }

    // T2: numbers differ, values match → false (a active)
    @Test
    public void testIsEqual_NumbersDiffer() {
        TGString s1 = makeString(1, 40);
        TGString s2 = makeString(2, 40);
        assertFalse(s1.isEqual(s2));
    }

    @Test
    public void testIsEqual_ValuesDiffer() {
        TGString s1 = makeString(1, 40);
        TGString s2 = makeString(1, 50);
        assertFalse(s1.isEqual(s2));
    }

    @Test
    public void testIsEqual_BothDiffer() {
        TGString s1 = makeString(1, 40);
        TGString s2 = makeString(2, 50);
        assertFalse(s1.isEqual(s2));
    }
}
