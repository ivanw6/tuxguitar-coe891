import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import app.tuxguitar.song.factory.TGFactory;
import app.tuxguitar.song.models.TGDivisionType;

public class TGDivisionTypeIsEqualTest {

    private TGDivisionType makeDivisionType(int enters, int times) {
        TGDivisionType dt = new TGFactory().newDivisionType();
        dt.setEnters(enters);
        dt.setTimes(times);
        return dt;
    }

    @Test
    public void testIsEqual_BothMatch() {
        TGDivisionType dt1 = makeDivisionType(3, 2);
        TGDivisionType dt2 = makeDivisionType(3, 2);
        assertTrue(dt1.isEqual(dt2));
    }

    @Test
    public void testIsEqual_EntersDiffer() {
        TGDivisionType dt1 = makeDivisionType(3, 2);
        TGDivisionType dt2 = makeDivisionType(5, 2);
        assertFalse(dt1.isEqual(dt2));
    }

    @Test
    public void testIsEqual_TimesDiffer() {
        TGDivisionType dt1 = makeDivisionType(3, 2);
        TGDivisionType dt2 = makeDivisionType(3, 4);
        assertFalse(dt1.isEqual(dt2));
    }

    @Test
    public void testIsEqual_BothDiffer() {
        TGDivisionType dt1 = makeDivisionType(3, 2);
        TGDivisionType dt2 = makeDivisionType(5, 4);
        assertFalse(dt1.isEqual(dt2));
    }
}
